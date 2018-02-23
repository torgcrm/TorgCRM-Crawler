package ru.torgcrm.crawler.parser;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import lombok.Getter;
import lombok.Setter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.torgcrm.crawler.domain.Crawler;
import ru.torgcrm.crawler.domain.FieldType;
import ru.torgcrm.crawler.domain.PageType;
import ru.torgcrm.crawler.domain.Value;
import ru.torgcrm.crawler.repository.FieldTypeRepository;
import ru.torgcrm.crawler.repository.PageRepository;
import ru.torgcrm.crawler.repository.PageTypeRepository;
import ru.torgcrm.crawler.repository.ValueRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@Scope("prototype")
public class WebsiteCrawler extends WebCrawler {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebsiteCrawler.class);

    private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|gif|jpg"
            + "|png|mp3|mp4|zip|gz))$");

    @Autowired
    private PageTypeRepository pageTypeRepository;
    @Autowired
    private FieldTypeRepository fieldTypeRepository;
    @Autowired
    private PageRepository pageRepository;
    @Autowired
    private ValueRepository valueRepository;
    @Getter
    @Setter
    private Crawler crawler;

    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        String href = url.getURL().toLowerCase();
        return !FILTERS.matcher(href).matches()
                && href.startsWith(crawler.getWebsite().getUrl());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void visit(Page page) {
        LOGGER.info("Visit new page " + page.getWebURL());
        if (page.getParseData() instanceof HtmlParseData) {
            String url = page.getWebURL().getURL();
            HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();

            // creating new page if not exist
            ru.torgcrm.crawler.domain.Page webPage = pageRepository
                    .findByUrlAndWebsiteId(url, crawler.getWebsite().getId());
            if (webPage == null) {
                webPage = new ru.torgcrm.crawler.domain.Page();
            }

            webPage.setContent(htmlParseData.getText().getBytes());
            webPage.setTitle(htmlParseData.getTitle());
            webPage.setDescription(htmlParseData.getMetaTags().get("description"));
            webPage.setKeywords(htmlParseData.getMetaTags().get("keywords"));
            webPage.setUrl(url);
            webPage.setWebsite(crawler.getWebsite());

            Document doc = Jsoup.parse(htmlParseData.getHtml());
            List<PageType> pageTypes = pageTypeRepository.findByWebsiteId(crawler.getWebsite().getId());
            for (PageType pageType : pageTypes) {
                String[] selectors = pageType.getSelectors().split(",");
                for (String selector : selectors) {
                    Element pageTypeEl = doc.select(selector).first();
                    if (pageTypeEl != null) {
                        webPage.setPageType(pageType);
                        break;
                    }
                }
            }
            if(webPage.getPageType() == null) {
                PageType defaultPageType = pageTypeRepository.findByCode(PageType.DEFAULT);
                webPage.setPageType(defaultPageType);
            }
            pageRepository.save(webPage);

            List<FieldType> pageFieldTypes = fieldTypeRepository.findByPageType(webPage.getPageType());
            List<Value> values = new ArrayList<>();
            for (FieldType fieldType : pageFieldTypes) {
                String[] selectors = fieldType.getSelectors().split(",");
                for (String selector : selectors) {
                    Element el = doc.select(selector).first();
                    if (el != null) {
                        String valueContent = el.text();
                        Value value = new Value();
                        value.setFieldType(fieldType);
                        value.setPage(webPage);
                        if (fieldType.getRegex() != null && !fieldType.getRegex().isEmpty()) {
                            Pattern r = Pattern.compile(fieldType.getRegex());
                            Matcher m = r.matcher(valueContent);
                            if (m.find()) {
                                int group = m.groupCount() > 1 ? m.groupCount() - 1 : 0;
                                value.setValue(m.group(group));
                            }
                        } else {
                            value.setValue(valueContent);
                        }
                        values.add(value);
                        valueRepository.save(value);
                        break;
                    }
                }
            }
            webPage.setValues(values);

            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                LOGGER.error("Can't sleep: " + e.getMessage());
            }
        }
    }
}

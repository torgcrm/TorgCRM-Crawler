package ru.torgcrm.crawler.controllers;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.exceptions.PageBiggerThanMaxSizeException;
import edu.uci.ics.crawler4j.fetcher.PageFetchResult;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.url.WebURL;
import org.apache.http.HttpStatus;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.SessionScope;
import ru.torgcrm.crawler.domain.*;
import ru.torgcrm.crawler.dto.WebsiteDTO;
import ru.torgcrm.crawler.mappers.WebsiteMapper;
import ru.torgcrm.crawler.model.WebsiteModel;
import ru.torgcrm.crawler.repository.PageTypeRepository;
import ru.torgcrm.crawler.repository.WebsiteRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@SessionScope
public class WebsitesController extends BaseController<WebsiteModel> {

    private static final String listPage = "/websites/websites.xhtml";
    private static final String addPage = "/websites/add.xhtml";
    private static final String pageTypesPage = "/websites/pagetypes.xhtml";
    private static final String fieldTypesPage = "/websites/fieldtypes.xhtml";
    private static final String parsedDataPage = "/websites/parseddata.xhtml";
    private static final String crawlerPage = "/websites/crawler.xhtml";
    private static final String fetchPage = "/websites/fetch-page.xhtml";

    @Autowired
    private WebsiteRepository websiteRepository;
    @Autowired
    private PageTypeRepository pageTypeRepository;
    private WebsiteMapper websiteMapper;

    public WebsitesController(WebsiteModel websiteModel,
                              WebsiteMapper websiteMapper) {
        this.setModel(websiteModel);
        this.websiteMapper = websiteMapper;
    }

    public void postAddToView() {
        List<WebsiteDTO> websites = websiteMapper.toDto(websiteRepository.findAll());
        getModel().setEntityList(websites);
        getModel().setSelected(null);
    }

    public void postValidate() {
        getModel().setRowSelected(false);
    }

    @Override
    public String onSave() {
        WebsiteDTO websiteDTO = getModel().getEntity();
        Website website = websiteMapper.toEntity(websiteDTO);
        if (website.getId() == null) {
            Crawler crawler = new Crawler();
            crawler.setCron("25 23 * * *"); // every hour
            crawler.setLastCrawlDate(new Date());
            website.setCrawler(crawler);

            // creating page types
            List<PageType> types = new ArrayList<>();
            PageType pageType = null;

            pageType = new PageType();
            pageType.setCode(PageType.CATALOG);
            pageType.setName("Catalog");
            pageType.setSelectors(".catalog");
            pageType.setWebsite(website);
            types.add(pageType);

            pageType = new PageType();
            pageType.setCode(PageType.DEFAULT);
            pageType.setName("Default");
            pageType.setSelectors(".page");
            pageType.setWebsite(website);
            types.add(pageType);

            pageType = new PageType();
            pageType.setCode(PageType.PRODUCT);
            pageType.setName("Product");
            pageType.setSelectors("[itemscope]");
            pageType.setWebsite(website);
            types.add(pageType);
            website.setPageTypes(types);

            List<FieldType> fieldTypes = new ArrayList<>();
            FieldType fieldType = new FieldType();
            fieldType.setCode(FieldType.PRODUCT_NAME);
            fieldType.setName("Product name");
            fieldType.setSelectors("h1");
            fieldType.setPageType(pageType);
            fieldType.setWebsite(website);
            fieldTypes.add(fieldType);

            fieldType = new FieldType();
            fieldType.setCode(FieldType.PRODUCT_CATEGORY);
            fieldType.setName("Product category");
            fieldType.setSelectors(".category");
            fieldType.setPageType(pageType);
            fieldType.setWebsite(website);
            fieldTypes.add(fieldType);

            fieldType = new FieldType();
            fieldType.setCode(FieldType.PRODUCT_PRICE);
            fieldType.setName("Product price");
            fieldType.setSelectors(".price");
            fieldType.setPageType(pageType);
            fieldType.setWebsite(website);
            fieldType.setRegex("\\D+");
            fieldTypes.add(fieldType);

            fieldType = new FieldType();
            fieldType.setCode(FieldType.PRODUCT_DESCRIPTION);
            fieldType.setName("Product description");
            fieldType.setSelectors(".description");
            fieldType.setPageType(pageType);
            fieldType.setWebsite(website);
            fieldTypes.add(fieldType);

            pageType.setFieldTypes(fieldTypes);
            website.setFieldTypes(fieldTypes);
        }
        websiteRepository.save(website);
        return listPage;
    }

    @Override
    public String onEdit() {
        getModel().setEntity(getModel().getSelected());
        return addPage;
    }

    @Override
    public String onAdd() {
        getModel().setEntity(new WebsiteDTO());
        return addPage;
    }

    @Override
    public String onDelete() {
        Website website = websiteMapper.toEntity(getModel().getSelected());
        websiteRepository.delete(website);
        getModel().setSelected(null);
        return listPage;
    }

    @Override
    public String onCancel() {
        return listPage;
    }

    public String onCrawlerSettings() {
        return crawlerPage;
    }

    public String onPageTypes() {
        return pageTypesPage;
    }

    public String onFieldTypes() {
        return fieldTypesPage;
    }

    public String onShowParsedData() {
        return parsedDataPage;
    }

    public String onOpenFetchPageForm() {
        getModel().setFetchedValues(null);
        getModel().setFetchUrl(null);
        getModel().setFetchTableRendered(false);
        return fetchPage;
    }

    public void onClickFetchPage() {
        try {
            String url = getModel().getFetchUrl();
            CrawlConfig config = new CrawlConfig();
            List<Value> values = new ArrayList<>();
            WebURL webURL = new WebURL();
            webURL.setURL(url);
            PageFetcher pageFetcher = new PageFetcher(config);
            PageFetchResult fetchResult = pageFetcher.fetchPage(webURL);
            if (fetchResult.getStatusCode() == HttpStatus.SC_OK) {
                Page page = new Page(webURL);
                fetchResult.fetchContent(page, 4096 * 1024);

                Website actualWebsite = websiteRepository.findById(getModel().getSelected().getId()).get();
                PageType currentPageType = null;
                Document doc = Jsoup.parse(new String(page.getContentData()));
                for (PageType pageType : actualWebsite.getPageTypes()) {
                    String[] selectors = pageType.getSelectors().split(",");
                    for (String selector : selectors) {
                        Element pageTypeEl = doc.select(selector).first();
                        if (pageTypeEl != null) {
                            currentPageType = pageType;
                            break;
                        }
                    }
                }
                if (currentPageType == null) {
                    currentPageType = pageTypeRepository.findByCode(PageType.DEFAULT);
                }

                for (FieldType fieldType : currentPageType.getFieldTypes()) {
                    String[] selectors = fieldType.getSelectors().split(",");
                    for (String selector : selectors) {
                        Element el = doc.select(selector).first();
                        if (el != null) {
                            String valueContent = el.text();
                            Value value = new Value();
                            value.setFieldType(fieldType);
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
                            break;
                        }
                    }
                }
            }
            getModel().setFetchedValues(values);
            getModel().setFetchTableRendered(values.size() > 0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (PageBiggerThanMaxSizeException e) {
            e.printStackTrace();
        }
    }
}

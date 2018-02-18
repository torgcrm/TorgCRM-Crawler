package ru.torgcrm.crawler.parser;


import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import lombok.Getter;
import org.springframework.context.ApplicationContext;
import ru.torgcrm.crawler.domain.Crawler;

public class WebsiteCrawlerFactory implements CrawlController.WebCrawlerFactory {

    @Getter
    private ApplicationContext applicationContext;
    private Crawler crawler;

    public WebsiteCrawlerFactory(ApplicationContext applicationContext, Crawler crawler) {
        this.applicationContext = applicationContext;
        this.crawler = crawler;
    }

    @Override
    public WebCrawler newInstance() {
        WebsiteCrawler websiteCrawler = applicationContext.getBean(WebsiteCrawler.class);
        websiteCrawler.setCrawler(crawler);
        return websiteCrawler;
    }
}

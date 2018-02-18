package ru.torgcrm.crawler.parser;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.torgcrm.crawler.domain.Crawler;
import ru.torgcrm.crawler.repository.CrawlerRepository;

import java.util.Date;

@Component
@Scope("prototype")
public class WebsiteParserRunnable implements Runnable {

    private final String userAgent = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:40.0) Gecko/20100101 Firefox/40.1";

    @Autowired
    private CrawlerRepository crawlerRepository;
    @Getter
    @Setter
    private Crawler crawler;
    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void run() {
        Thread.currentThread().setName("Crawler: " + crawler.getWebsite().getUrl());
        try {
            crawler.setLastCrawlDate(new Date());
            crawlerRepository.save(crawler);

            String crawlStorageFolder = "/tmp/crawler";
            int numberOfCrawlers = 5; // thread count to parse website

            CrawlConfig config = new CrawlConfig();
            config.setMaxConnectionsPerHost(1);
            config.setMaxTotalConnections(1);
            config.setConnectionTimeout(20000);
            config.setSocketTimeout(5000);
            config.setCrawlStorageFolder(crawlStorageFolder);

            PageFetcher pageFetcher = new PageFetcher(config);
            RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
            RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
            CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

            WebsiteCrawlerFactory websiteCrawlerFactory =
                    new WebsiteCrawlerFactory(applicationContext, crawler);
            controller.addSeed(crawler.getWebsite().getUrl());
            controller.start(websiteCrawlerFactory, numberOfCrawlers);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

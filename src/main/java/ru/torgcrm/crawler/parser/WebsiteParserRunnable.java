package ru.torgcrm.crawler.parser;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.torgcrm.crawler.domain.Crawler;
import ru.torgcrm.crawler.repository.CrawlerRepository;

@Component
@Scope("prototype")
public class WebsiteParserRunnable implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebsiteParserRunnable.class);
    private final String userAgent = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:40.0) Gecko/20100101 Firefox/40.1";

    @Autowired
    private CrawlerRepository crawlerRepository;
    @Value("${crawler.quantity}")
    private Integer numberOfCrawlers = 1;
    @Value("${crawler.max-connections-per-host}")
    private Integer maxConnectionsPerHos = 1;
    @Value("${crawler.max-total-connections}")
    private Integer maxTotalConnections = 1;
    @Value("${crawler.connection-timeout}")
    private Integer connectionTimeOut = 20000;
    @Value("${crawler.socket-timeout}")
    private Integer socketTimeOut = 5000;
    @Getter
    @Setter
    private Crawler crawler;
    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void run() {
        Thread.currentThread().setName("Crawler: " + crawler.getWebsite().getUrl());
        try {
            String crawlStorageFolder = "/tmp/crawler";

            CrawlConfig config = new CrawlConfig();
            config.setMaxConnectionsPerHost(maxConnectionsPerHos);
            config.setMaxTotalConnections(maxTotalConnections);
            config.setConnectionTimeout(connectionTimeOut);
            config.setSocketTimeout(socketTimeOut);
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
            LOGGER.error("Can't visit page: " + e.getCause());
        }
    }
}

package ru.torgcrm.crawler.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.torgcrm.crawler.parser.WebsiteCrawler;
import ru.torgcrm.crawler.parser.WebsiteParserRunnable;
import ru.torgcrm.crawler.repository.CrawlerRepository;
import ru.torgcrm.crawler.utils.CronUtils;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Component
public class CrawlerScheduler {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebsiteCrawler.class);

    @Autowired
    private CrawlerRepository crawlerRepository;
    @Autowired
    private TaskExecutor crawlerExecutor;
    @Autowired
    private ApplicationContext applicationContext;

    @Scheduled(fixedRate = 5000)
    public void crawl() {
        crawlerRepository.findAll().forEach(crawler -> {
            ZonedDateTime ld = ZonedDateTime.ofInstant(crawler.getLastCrawlDate().toInstant(), ZoneId.systemDefault());
            if (crawler.getLastCrawlDate() == null ||
                    CronUtils.lastExecution(crawler.getCron()).compareTo(ld) >= 1) {
                WebsiteParserRunnable websiteParserRunnable =
                        applicationContext.getBean(WebsiteParserRunnable.class);
                websiteParserRunnable.setCrawler(crawler);
                crawlerExecutor.execute(websiteParserRunnable);
            }
        });
    }
}

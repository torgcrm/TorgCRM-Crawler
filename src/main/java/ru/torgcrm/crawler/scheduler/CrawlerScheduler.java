package ru.torgcrm.crawler.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.torgcrm.crawler.parser.WebsiteParserRunnable;
import ru.torgcrm.crawler.repository.CrawlerRepository;
import ru.torgcrm.crawler.utils.CronUtils;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

@Component
public class CrawlerScheduler {

    @Autowired
    private CrawlerRepository crawlerRepository;
    @Autowired
    private TaskExecutor crawlerExecutor;
    @Autowired
    private ApplicationContext applicationContext;

    @Scheduled(fixedRate = 5000)
    public void crawl() {
        System.out.println("Let's schedule websites");
        crawlerRepository.findAll().forEach(crawler -> {
            ZonedDateTime ld = ZonedDateTime.ofInstant(crawler.getLastCrawlDate().toInstant(), ZoneId.systemDefault());
            if (crawler.getLastCrawlDate() == null ||
                    CronUtils.lastExecution(crawler.getCron()).compareTo(ld) >= 1) {
                crawler.setLastCrawlDate(new Date());
                crawlerRepository.save(crawler);

                WebsiteParserRunnable websiteParserRunnable =
                        applicationContext.getBean(WebsiteParserRunnable.class);
                websiteParserRunnable.setCrawler(crawler);
                crawlerExecutor.execute(websiteParserRunnable);
            }
        });
    }
}

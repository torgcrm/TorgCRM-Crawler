package ru.torgcrm.crawler.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import java.util.Date;
import java.util.Set;

@Component
public class CrawlerScheduler {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebsiteCrawler.class);

    @Autowired
    private CrawlerRepository crawlerRepository;
    @Autowired
    private TaskExecutor crawlerExecutor;
    @Autowired
    private ApplicationContext applicationContext;
    @Value("${crawler.viewOnly}")
    private Boolean viewOnly = false;
    @Value("${crawler.maximumCrawlers}")
    private Integer maximumCrawlers = 1;

    @Scheduled(fixedRate = 5000)
    public void crawl() {
        if (maximumCrawlers == 1) {
            Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
            for (Thread thread : threadSet) {
                if (thread.getName().startsWith("Crawler:")) {
                    return;
                }
            }
        }

        if (!viewOnly) { // run when is not view only. you can run another instance, just for view results
            crawlerRepository.findAll().forEach(crawler -> {
                ZonedDateTime ld = ZonedDateTime.ofInstant(crawler.getLastCrawlDate().toInstant(), ZoneId.systemDefault());
                if (crawler.getLastCrawlDate() == null ||
                        CronUtils.lastExecution(crawler.getCron()).compareTo(ld) >= 1) {
                    try {
                        WebsiteParserRunnable websiteParserRunnable =
                                applicationContext.getBean(WebsiteParserRunnable.class);
                        websiteParserRunnable.setCrawler(crawler);
                        crawlerExecutor.execute(websiteParserRunnable);

                        crawler.setLastCrawlDate(new Date());
                        crawlerRepository.save(crawler);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}

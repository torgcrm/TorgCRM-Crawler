package ru.torgcrm.crawler.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CrawlerScheduler {
    @Scheduled(fixedRate = 5000)
    public void crawl() {
        System.out.println("Let's schedule websites");
    }
}

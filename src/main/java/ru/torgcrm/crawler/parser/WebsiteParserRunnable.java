package ru.torgcrm.crawler.parser;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.torgcrm.crawler.domain.Crawler;
import ru.torgcrm.crawler.repository.CrawlerRepository;

@Component
@Scope("prototype")
public class WebsiteParserRunnable implements Runnable {
    @Autowired
    private CrawlerRepository crawlerRepository;
    @Getter
    @Setter
    private Crawler crawler;

    @Override
    public void run() {
        System.out.println(crawler.getWebsite());
        crawler.getWebsite().getId();
    }
}

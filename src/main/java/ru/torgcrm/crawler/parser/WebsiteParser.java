package ru.torgcrm.crawler.parser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import ru.torgcrm.crawler.domain.Crawler;
import ru.torgcrm.crawler.repository.CrawlerRepository;

@Configurable
public class WebsiteParser implements Runnable {
    private Crawler crawler;

    @Autowired
    private CrawlerRepository crawlerRepository;

    public WebsiteParser(Crawler crawler) {
        this.crawler = crawler;
    }

    @Override
    public void run() {
        Crawler crawler = crawlerRepository.findById(this.crawler.getId()).get();
        crawler.getWebsite().getId();
    }
}

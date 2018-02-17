package ru.torgcrm.crawler.dto;

import lombok.Getter;
import lombok.Setter;
import ru.torgcrm.crawler.domain.Website;

import java.util.Date;

public class CrawlerDTO extends BaseDTO {
    @Getter
    @Setter
    private String cron;
    @Getter
    @Setter
    private Date lastCrawlDate;
    @Getter
    @Setter
    private Website website;
}

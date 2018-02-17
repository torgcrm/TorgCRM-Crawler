package ru.torgcrm.crawler.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class CrawlerDTO extends BaseDTO {
    @Getter
    @Setter
    private String cron;
    @Getter
    @Setter
    private Date lastCrawlDate;
}

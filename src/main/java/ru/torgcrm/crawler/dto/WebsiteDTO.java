package ru.torgcrm.crawler.dto;

import lombok.Getter;
import lombok.Setter;
import ru.torgcrm.crawler.domain.Crawler;

public class WebsiteDTO extends BaseDTO {
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String url;
    @Getter
    @Setter
    private String code;
    @Getter
    @Setter
    private String description;
    @Getter
    @Setter
    private Crawler crawler;
}

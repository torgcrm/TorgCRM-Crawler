package ru.torgcrm.crawler.dto;

import lombok.Getter;
import lombok.Setter;

public class WebsiteDTO {
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String url;
    @Getter
    @Setter
    private String code;
}

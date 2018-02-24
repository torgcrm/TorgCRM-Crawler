package ru.torgcrm.crawler.dto;

import lombok.Getter;
import lombok.Setter;
import ru.torgcrm.crawler.domain.Value;
import ru.torgcrm.crawler.domain.Website;

import java.util.List;

public class PageDTO extends BaseDTO {
    @Getter
    @Setter
    private String url;
    @Getter
    @Setter
    private String title;
    @Getter
    @Setter
    private String description;
    @Getter
    @Setter
    private String keywords;
    @Getter
    @Setter
    private byte[] content;
    @Getter
    @Setter
    private PageTypeDTO pageType;
    @Getter
    @Setter
    private List<Value> values;
    @Getter
    @Setter
    private Website website;
}
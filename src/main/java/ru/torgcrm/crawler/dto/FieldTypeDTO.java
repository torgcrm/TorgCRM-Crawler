package ru.torgcrm.crawler.dto;

import lombok.Getter;
import lombok.Setter;

public class FieldTypeDTO extends BaseDTO {
    @Getter
    @Setter
    private String selectors;
    @Getter
    @Setter
    private String regex;
    @Getter
    @Setter
    private PageTypeDTO pageType;
    @Getter
    @Setter
    private WebsiteDTO website;
}

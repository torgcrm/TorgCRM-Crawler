package ru.torgcrm.crawler.dto;

import lombok.Getter;
import lombok.Setter;
import ru.torgcrm.crawler.domain.PageType;

public class FieldTypeDTO extends BaseDTO {
    @Getter
    @Setter
    private String selectors;
    @Getter
    @Setter
    private String regex;
}

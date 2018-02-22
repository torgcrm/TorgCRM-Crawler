package ru.torgcrm.crawler.dto;

import lombok.Getter;
import lombok.Setter;
import ru.torgcrm.crawler.domain.FieldType;
import ru.torgcrm.crawler.domain.Website;

import java.util.List;

public class PageTypeDTO extends BaseDTO {
    @Getter
    @Setter
    private String selectors;
    @Getter
    @Setter
    private WebsiteDTO website;
    @Getter
    @Setter
    private List<FieldTypeDTO> fieldTypes;
}

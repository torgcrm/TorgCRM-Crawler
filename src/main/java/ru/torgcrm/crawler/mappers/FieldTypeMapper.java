package ru.torgcrm.crawler.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.torgcrm.crawler.domain.FieldType;
import ru.torgcrm.crawler.domain.PageType;
import ru.torgcrm.crawler.domain.Website;
import ru.torgcrm.crawler.dto.FieldTypeDTO;
import ru.torgcrm.crawler.dto.PageTypeDTO;
import ru.torgcrm.crawler.dto.WebsiteDTO;

@Mapper(componentModel = "spring")
public interface FieldTypeMapper extends BaseMapper<FieldTypeDTO, FieldType> {
    WebsiteDTO fromWebsite(Website website);

    Website fromWebsiteDto(WebsiteDTO websiteDTO);

    @Mapping(target = "fieldTypes", ignore = true)
    PageType fromPageTypeDto(PageTypeDTO pageTypeDTO);

    @Mapping(target = "fieldTypes", ignore = true)
    PageTypeDTO fromPageType(PageType pageType);
}

package ru.torgcrm.crawler.mappers;

import org.mapstruct.Mapper;
import ru.torgcrm.crawler.domain.FieldType;
import ru.torgcrm.crawler.domain.PageType;
import ru.torgcrm.crawler.domain.Website;
import ru.torgcrm.crawler.dto.FieldTypeDTO;
import ru.torgcrm.crawler.dto.PageTypeDTO;
import ru.torgcrm.crawler.dto.WebsiteDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PageTypeMapper extends BaseMapper<PageTypeDTO, PageType> {
    WebsiteDTO fromWebsite(Website website);

    Website fromWebsiteDto(WebsiteDTO websiteDTO);

    FieldTypeDTO fieldTypeToFieldTypeDto(FieldType fieldType);

    List<FieldTypeDTO> fieldTypesToFieldTypeDtos(List<FieldType> fieldTypes);
}

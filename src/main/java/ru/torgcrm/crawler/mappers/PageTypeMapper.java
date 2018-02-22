package ru.torgcrm.crawler.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
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

    @Mapping(target = "pageType", ignore = true)
    FieldTypeDTO fieldTypeToFieldTypeDto(FieldType fieldType);

    @Mapping(target = "pageType", ignore = true)
    FieldType fieldTypeDtoToFieldType(FieldTypeDTO fieldType);

    List<FieldTypeDTO> fieldTypesToFieldTypeDtos(List<FieldType> fieldTypes);

    List<FieldType> fieldTypeDtosToFieldTypes(List<FieldTypeDTO> fieldTypeDtos);
}

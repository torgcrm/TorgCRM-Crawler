package ru.torgcrm.crawler.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.torgcrm.crawler.domain.Page;
import ru.torgcrm.crawler.domain.PageType;
import ru.torgcrm.crawler.dto.PageDTO;
import ru.torgcrm.crawler.dto.PageTypeDTO;

@Mapper(componentModel = "spring")
public interface PageMapper extends BaseMapper<PageDTO, Page> {
    @Mapping(target = "fieldTypes", ignore = true)
    PageType fromPageTypeDto(PageTypeDTO pageTypeDTO);

    @Mapping(target = "fieldTypes", ignore = true)
    PageTypeDTO fromPageType(PageType pageType);
}

package ru.torgcrm.crawler.mappers;

import org.mapstruct.Mapper;
import ru.torgcrm.crawler.domain.PageType;
import ru.torgcrm.crawler.dto.PageTypeDTO;

@Mapper(componentModel = "spring")
public interface PageTypeMapper extends BaseMapper<PageTypeDTO, PageType> {
}

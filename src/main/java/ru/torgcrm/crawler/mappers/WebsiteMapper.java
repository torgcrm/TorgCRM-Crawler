package ru.torgcrm.crawler.mappers;

import org.mapstruct.Mapper;
import ru.torgcrm.crawler.domain.Website;
import ru.torgcrm.crawler.dto.WebsiteDTO;

@Mapper(componentModel = "spring")
public interface WebsiteMapper extends BaseMapper<WebsiteDTO, Website> {
}

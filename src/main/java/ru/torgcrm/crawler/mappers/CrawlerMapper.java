package ru.torgcrm.crawler.mappers;

import org.mapstruct.Mapper;
import ru.torgcrm.crawler.domain.Crawler;
import ru.torgcrm.crawler.domain.Website;
import ru.torgcrm.crawler.dto.CrawlerDTO;
import ru.torgcrm.crawler.dto.WebsiteDTO;

@Mapper(componentModel = "spring")
public interface CrawlerMapper extends BaseMapper<CrawlerDTO, Crawler> {
}

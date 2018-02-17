package ru.torgcrm.crawler.model;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import ru.torgcrm.crawler.dto.CrawlerDTO;

@Component
@SessionScope
public class WebsiteCrawlerModel extends BaseModel<CrawlerDTO> {
}

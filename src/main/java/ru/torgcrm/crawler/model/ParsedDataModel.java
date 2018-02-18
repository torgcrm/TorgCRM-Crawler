package ru.torgcrm.crawler.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import ru.torgcrm.crawler.domain.PageType;
import ru.torgcrm.crawler.dto.WebsiteDTO;

import java.util.List;
import java.util.Map;

@Component
@SessionScope
public class ParsedDataModel extends BaseModel<WebsiteDTO> {
    @Getter
    @Setter
    private List<PageType> pageTypes;
    @Getter
    @Setter
    private Map<String, Integer> pageTypeCounter;
}

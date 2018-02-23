package ru.torgcrm.crawler.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import ru.torgcrm.crawler.domain.Value;
import ru.torgcrm.crawler.dto.WebsiteDTO;

import java.util.List;

@Component
@SessionScope
public class WebsiteModel extends BaseModel<WebsiteDTO> {
    @Getter
    @Setter
    private String fetchUrl;
    @Getter
    @Setter
    private boolean fetchTableRendered;
    @Getter
    @Setter
    private List<Value> fetchedValues;
}

package ru.torgcrm.crawler.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import ru.torgcrm.crawler.dto.WebsiteDTO;

@Component
@SessionScope
public class WebsiteModel extends BaseModel<WebsiteDTO> {
    @Getter
    @Setter
    private String fetchUrl;
    @Getter
    @Setter
    private boolean fetchTableRendered;
}

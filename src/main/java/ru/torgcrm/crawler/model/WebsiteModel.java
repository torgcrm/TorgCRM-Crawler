package ru.torgcrm.crawler.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import ru.torgcrm.crawler.dto.WebsiteDTO;

import java.util.List;

@Component
@SessionScope
public class WebsiteModel extends BaseModel {
    @Getter
    @Setter
    private List<WebsiteDTO> websites;
    @Getter
    @Setter
    private WebsiteDTO website;
}

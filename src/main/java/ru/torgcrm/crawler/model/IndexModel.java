package ru.torgcrm.crawler.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class IndexModel extends BaseModel {
    @Getter
    @Setter
    private String indexHello = "Hello index";
}

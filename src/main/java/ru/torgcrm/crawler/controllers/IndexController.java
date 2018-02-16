package ru.torgcrm.crawler.controllers;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import ru.torgcrm.crawler.model.IndexModel;


@Component
@SessionScope
public class IndexController extends BaseController {
    public IndexController(IndexModel model) {
        this.setModel(model);
    }
}

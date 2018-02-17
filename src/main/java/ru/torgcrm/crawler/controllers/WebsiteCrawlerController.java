package ru.torgcrm.crawler.controllers;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class WebsiteCrawlerController extends BaseController {

    public void postAddToView() {
        getModel().setSelected(null);
    }

    public void postValidate() {
        getModel().setRowSelected(false);
    }

    @Override
    public String onSave() {
        return null;
    }

    @Override
    public String onEdit() {
        return null;
    }

    @Override
    public String onAdd() {
        return null;
    }

    @Override
    public String onDelete() {
        return null;
    }
}

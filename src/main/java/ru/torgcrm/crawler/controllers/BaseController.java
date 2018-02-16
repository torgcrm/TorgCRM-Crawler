package ru.torgcrm.crawler.controllers;

import lombok.Getter;
import lombok.Setter;
import ru.torgcrm.crawler.model.BaseModel;

public abstract class BaseController {
    @Getter
    @Setter
    private BaseModel model;

    public abstract void onSave();
}

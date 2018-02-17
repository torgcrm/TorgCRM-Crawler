package ru.torgcrm.crawler.controllers;

import lombok.Getter;
import lombok.Setter;
import ru.torgcrm.crawler.model.BaseModel;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public abstract class BaseController<T extends BaseModel> {
    @Getter
    @Setter
    private T model;

    public void postAddToView() {
        throw new NotImplementedException();
    }

    public void postValidate() {
        throw new NotImplementedException();
    }

    public abstract String onSave();

    public abstract String onEdit();

    public abstract String onAdd();

    public abstract String onDelete();

    public String onCancel() {
        throw new NotImplementedException();
    }

    public void onRowSelect() {
        model.setRowSelected(true);
    }
}

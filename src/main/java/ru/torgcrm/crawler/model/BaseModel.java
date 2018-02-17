package ru.torgcrm.crawler.model;

import lombok.Getter;
import lombok.Setter;
import ru.torgcrm.crawler.dto.BaseDTO;

import java.util.List;

public abstract class BaseModel<T extends BaseDTO> {
    @Getter
    @Setter
    private T selected;
    @Getter
    @Setter
    private T entity;
    @Getter
    @Setter
    private List<T> entityList;
    @Getter
    @Setter
    private boolean rowSelected;
}

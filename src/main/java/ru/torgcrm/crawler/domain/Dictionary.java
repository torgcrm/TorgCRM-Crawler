package ru.torgcrm.crawler.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Dictionary extends BasicEntity {
    @Getter
    @Setter
    private String code;
    @Getter
    @Setter
    private String name;
}

package ru.torgcrm.crawler.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
public abstract class BasicEntity {
    @Getter
    @Setter
    private Date createdDate;
    @Getter
    @Setter
    private Date updatedDate;

    public abstract Long getId();
}

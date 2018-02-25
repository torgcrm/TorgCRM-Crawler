package ru.torgcrm.crawler.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
public abstract class BasicEntity {
    @Getter
    @Setter
    @CreationTimestamp
    private Date createdDate;
    @Getter
    @Setter
    @UpdateTimestamp
    private Date updatedDate;

    public abstract Long getId();
}

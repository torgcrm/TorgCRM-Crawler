package ru.torgcrm.crawler.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "page_types")
public class PageType extends Dictionary {
    public static final String GEN_NAME = "Gen_PageType";
    public static final String SEQ_NAME = "Seq_PageType";

    @Id
    @Setter
    @SequenceGenerator(sequenceName = SEQ_NAME, name = GEN_NAME)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = GEN_NAME)
    private Long id;
    @Getter
    @Setter
    private String xpath;

    @Override
    public Long getId() {
        return this.id;
    }
}

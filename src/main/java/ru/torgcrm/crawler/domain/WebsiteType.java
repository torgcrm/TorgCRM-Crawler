package ru.torgcrm.crawler.domain;

import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "website_types")
public class WebsiteType extends Dictionary {
    public static final String GEN_NAME = "Gen_WebsiteType";
    public static final String SEQ_NAME = "Seq_WebsiteType";

    @Id
    @Setter
    @SequenceGenerator(sequenceName = SEQ_NAME, name = GEN_NAME)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = GEN_NAME)
    private Long id;

    @Override
    public Long getId() {
        return this.id;
    }
}

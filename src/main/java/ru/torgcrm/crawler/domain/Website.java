package ru.torgcrm.crawler.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "websites")
public class Website extends Dictionary {
    public static final String GEN_NAME = "Gen_Website";
    public static final String SEQ_NAME = "Seq_Website";

    @Id
    @Setter
    @SequenceGenerator(sequenceName = SEQ_NAME, name = GEN_NAME)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = GEN_NAME)
    private Long id;
    @Getter
    @Setter
    private String url;

    @Override
    public Long getId() {
        return this.id;
    }
}

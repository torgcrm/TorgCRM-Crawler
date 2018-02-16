package ru.torgcrm.crawler.domain;

import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "field_types")
public class FieldType extends Dictionary {
    public static final String GEN_NAME = "Gen_FieldType";
    public static final String SEQ_NAME = "Seq_FieldType";

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

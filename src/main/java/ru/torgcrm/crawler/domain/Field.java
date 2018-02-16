package ru.torgcrm.crawler.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "page_fields")
public class Field extends Dictionary {
    public static final String GEN_NAME = "Gen_Field";
    public static final String SEQ_NAME = "Seq_Field";

    @Id
    @Setter
    @SequenceGenerator(sequenceName = SEQ_NAME, name = GEN_NAME)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = GEN_NAME)
    private Long id;

    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String code;
    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name = "field_type_id", referencedColumnName = "id")
    private FieldType fieldType;

    @Override
    public Long getId() {
        return this.id;
    }
}

package ru.torgcrm.crawler.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "page_values")
public class Value extends Dictionary {
    public static final String GEN_NAME = "Gen_Field";
    public static final String SEQ_NAME = "Seq_Field";

    @Id
    @Setter
    @SequenceGenerator(sequenceName = SEQ_NAME, name = GEN_NAME)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = GEN_NAME)
    private Long id;

    @Getter
    @Setter
    @Lob
    private String value;
    @Getter
    @Setter
    @ManyToOne
    private FieldType fieldType;
    @Getter
    @Setter
    @ManyToOne
    private Page page;
    @Getter
    @Setter
    @ManyToOne
    private Website website;

    @Override
    public Long getId() {
        return this.id;
    }
}

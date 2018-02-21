package ru.torgcrm.crawler.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

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
    @Column(columnDefinition = "TEXT")
    private String value;
    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "field_type_id")
    private FieldType fieldType;
    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "page_id")
    private Page page;
    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "website_id")
    private Website website;

    @Override
    public Long getId() {
        return this.id;
    }
}

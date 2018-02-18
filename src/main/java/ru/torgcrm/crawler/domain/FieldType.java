package ru.torgcrm.crawler.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "field_types")
public class FieldType extends Dictionary {
    public static final String GEN_NAME = "Gen_FieldType";
    public static final String SEQ_NAME = "Seq_FieldType";
    public static final String PRODUCT_NAME = "product_name";
    public static final String PRODUCT_CATEGORY = "product_category";
    public static final String PRODUCT_PRICE = "product_price";
    public static final String PRODUCT_DESCRIPTION = "product_description";

    @Id
    @Setter
    @SequenceGenerator(sequenceName = SEQ_NAME, name = GEN_NAME)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = GEN_NAME)
    private Long id;
    @Getter
    @Setter
    private String selectors;
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

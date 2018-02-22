package ru.torgcrm.crawler.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "page_types")
public class PageType extends Dictionary {
    public static final String GEN_NAME = "Gen_PageType";
    public static final String SEQ_NAME = "Seq_PageType";
    public static final String PRODUCT = "product";
    public static final String CATALOG = "catalog";
    public static final String DEFAULT = "default";

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
    @Getter
    @Setter
    @OneToMany(mappedBy = "pageType")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<FieldType> fieldTypes;

    @Override
    public Long getId() {
        return this.id;
    }
}

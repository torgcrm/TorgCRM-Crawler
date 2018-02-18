package ru.torgcrm.crawler.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "pages")
public class Page extends BasicEntity {
    public static final String GEN_NAME = "Gen_Page";
    public static final String SEQ_NAME = "Seq_Page";

    @Id
    @Setter
    @SequenceGenerator(sequenceName = SEQ_NAME, name = GEN_NAME)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = GEN_NAME)
    private Long id;
    @Getter
    @Setter
    private String url;
    @Getter
    @Setter
    private String title;
    @Getter
    @Setter
    private String description;
    @Getter
    @Setter
    private String keywords;
    @Getter
    @Setter
    @Lob
    private String content;
    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name = "page_type_id", referencedColumnName = "id")
    private PageType pageType;
    @Getter
    @Setter
    @OneToMany(mappedBy = "page")
    private List<Value> values;
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

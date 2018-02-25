package ru.torgcrm.crawler.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

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
    @Getter
    @Setter
    private String description;
    @Getter
    @Setter
    @OneToOne(cascade = CascadeType.PERSIST)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinTable(name = "website_crawler",
            joinColumns = {@JoinColumn(name = "website_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "crawler_id", referencedColumnName = "id")}
    )
    private Crawler crawler;
    @Getter
    @Setter
    @OneToMany(mappedBy = "website", cascade = CascadeType.PERSIST)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<PageType> pageTypes;
    @Getter
    @Setter
    @OneToMany(mappedBy = "website", cascade = CascadeType.PERSIST)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<FieldType> fieldTypes;
    @Getter
    @Setter
    @OneToMany(mappedBy = "website", cascade = CascadeType.PERSIST)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Value> values;
    @Getter
    @Setter
    @OneToMany(mappedBy = "website", cascade = CascadeType.PERSIST)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Page> pages;

    @Override
    public Long getId() {
        return this.id;
    }
}

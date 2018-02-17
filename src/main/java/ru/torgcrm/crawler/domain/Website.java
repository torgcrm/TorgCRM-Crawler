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
    @Getter
    @Setter
    private String description;
    @Getter
    @Setter
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinTable(name = "website_crawler",
            joinColumns = {@JoinColumn(name = "website_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "crawler_id", referencedColumnName = "id")}
    )
    private Crawler crawler;

    @Override
    public Long getId() {
        return this.id;
    }
}

package ru.torgcrm.crawler.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "crawlers")
public class Crawler extends BasicEntity {
    public static final String GEN_NAME = "Gen_Crawler";
    public static final String SEQ_NAME = "Seq_Crawler";

    @Id
    @Setter
    @SequenceGenerator(sequenceName = SEQ_NAME, name = GEN_NAME)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = GEN_NAME)
    private Long id;

    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name = "website_id")
    private Website website;

    @Getter
    @Setter
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastCrawlDate;
    @Getter
    @Setter
    private String cron;

    @Override
    public Long getId() {
        return this.id;
    }
}

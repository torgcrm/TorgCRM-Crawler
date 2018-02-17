package ru.torgcrm.crawler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.torgcrm.crawler.domain.Crawler;

@Repository
public interface CrawlerRepository extends JpaRepository<Crawler, Long> {
    Crawler findByWebsiteId(Long websiteId);
}

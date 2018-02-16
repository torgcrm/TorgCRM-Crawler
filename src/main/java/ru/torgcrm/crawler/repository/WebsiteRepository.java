package ru.torgcrm.crawler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.torgcrm.crawler.domain.Website;

@Repository
public interface WebsiteRepository extends JpaRepository<Website, Long> {
}

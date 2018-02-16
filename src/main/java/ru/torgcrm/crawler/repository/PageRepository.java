package ru.torgcrm.crawler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.torgcrm.crawler.domain.Page;

@Repository
public interface PageRepository extends JpaRepository<Page, Long> {
}

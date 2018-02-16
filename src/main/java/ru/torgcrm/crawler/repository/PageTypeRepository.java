package ru.torgcrm.crawler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.torgcrm.crawler.domain.PageType;

@Repository
public interface PageTypeRepository extends JpaRepository<PageType, Long> {
}

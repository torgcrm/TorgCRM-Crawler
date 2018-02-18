package ru.torgcrm.crawler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.torgcrm.crawler.domain.PageType;

import java.util.List;

@Repository
public interface PageTypeRepository extends JpaRepository<PageType, Long> {
    List<PageType> findByWebsiteId(Long websiteId);
    PageType findByCode(String code);
}

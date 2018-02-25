package ru.torgcrm.crawler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.torgcrm.crawler.domain.FieldType;
import ru.torgcrm.crawler.domain.PageType;

import java.util.List;

@Repository
public interface PageTypeRepository extends JpaRepository<PageType, Long> {
    List<FieldType> findAllByOrderByIdDesc();

    List<PageType> findByWebsiteId(Long websiteId);

    List<PageType> findByWebsiteIdOrderByIdDesc(Long websiteId);

    PageType findByCodeAndWebsiteId(String code, Long websiteId);
}

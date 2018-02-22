package ru.torgcrm.crawler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.torgcrm.crawler.domain.FieldType;
import ru.torgcrm.crawler.domain.PageType;

import java.util.List;

@Repository
public interface FieldTypeRepository extends JpaRepository<FieldType, Long> {

    List<FieldType> findAllByOrderByIdDesc();

    List<FieldType> findByWebsiteId(Long websiteId);

    List<FieldType> findByPageType(PageType pageType);
}

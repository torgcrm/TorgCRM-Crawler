package ru.torgcrm.crawler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.torgcrm.crawler.domain.FieldType;
import ru.torgcrm.crawler.domain.WebsiteType;

import java.util.List;

@Repository
public interface WebsiteTypeRepository extends JpaRepository<WebsiteType, Long> {
    List<FieldType> findAllByOrderByIdDesc();
}

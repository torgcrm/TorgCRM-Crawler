package ru.torgcrm.crawler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.torgcrm.crawler.domain.FieldType;
import ru.torgcrm.crawler.domain.Website;

import java.util.List;

@Repository
public interface WebsiteRepository extends JpaRepository<Website, Long> {
    List<FieldType> findAllByOrderByIdDesc();
}

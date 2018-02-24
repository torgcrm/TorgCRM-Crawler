package ru.torgcrm.crawler.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.torgcrm.crawler.domain.Page;
import ru.torgcrm.crawler.domain.PageType;

import java.util.List;

@Repository
public interface PageRepository extends JpaRepository<Page, Long> {

    Page findByUrlAndWebsiteId(String url, Long websiteId);

    Integer countByPageType(PageType pageType);

    Integer countByPageTypeId(Long pageTypeId);

    Integer countByPageTypeIdAndTitleContaining(Long pageTypeId, String title);

    List<Page> findAllByPageTypeId(Long pageTypeId, Pageable page);

    List<Page> findAllByPageTypeIdAndTitleContaining(Long pageTypeId, String title, Pageable page);
}

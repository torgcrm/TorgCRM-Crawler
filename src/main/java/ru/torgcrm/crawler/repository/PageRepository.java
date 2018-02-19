package ru.torgcrm.crawler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.torgcrm.crawler.domain.Page;
import ru.torgcrm.crawler.domain.PageType;
import ru.torgcrm.crawler.domain.Website;

import java.util.List;

@Repository
public interface PageRepository extends JpaRepository<Page, Long> {
    Page findByUrlAndWebsiteId(String url, Long websiteId);

    List<Page> findByWebsiteId(Long websiteId);

    List<Page> findByWebsiteAndPageType(Website website, PageType pageType);

    List<Page> findByPageType(PageType pageType);

    Integer countByPageType(PageType pageType);
}

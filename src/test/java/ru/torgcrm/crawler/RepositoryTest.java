package ru.torgcrm.crawler;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.torgcrm.crawler.domain.Crawler;
import ru.torgcrm.crawler.domain.FieldType;
import ru.torgcrm.crawler.domain.PageType;
import ru.torgcrm.crawler.domain.Website;
import ru.torgcrm.crawler.repository.FieldTypeRepository;
import ru.torgcrm.crawler.repository.PageTypeRepository;
import ru.torgcrm.crawler.repository.WebsiteRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RepositoryTest {

    @Autowired
    private WebsiteRepository websiteRepository;
    @Autowired
    private PageTypeRepository pageTypeRepository;
    @Autowired
    private FieldTypeRepository fieldTypeRepository;

    @Test
    public void createWebsiteWithPageTypesAndFields() {
        Website website = new Website();
        website.setName("test");
        website.setUrl("http://test.com");

        Crawler crawler = new Crawler();
        crawler.setCron("25 23 * * *"); // every hour
        crawler.setLastCrawlDate(new Date());
        website.setCrawler(crawler);

        // creating page types
        List<PageType> types = new ArrayList<>();
        PageType pageType = new PageType();
        pageType.setCode(PageType.PRODUCT);
        pageType.setName("Product");
        pageType.setSelectors("[itemscope]");
        types.add(pageType);
        pageType.setWebsite(website);

        pageType = new PageType();
        pageType.setCode(PageType.CATALOG);
        pageType.setName("Catalog");
        pageType.setSelectors(".catalog");
        types.add(pageType);
        pageType.setWebsite(website);

        pageType = new PageType();
        pageType.setCode(PageType.DEFAULT);
        pageType.setName("Default");
        pageType.setSelectors(".page");
        pageType.setWebsite(website);

        types.add(pageType);
        website.setPageTypes(types);

        List<FieldType> fieldTypes = new ArrayList<>();
        FieldType fieldType = new FieldType();
        fieldType.setCode(FieldType.PRODUCT_NAME);
        fieldType.setName("Product name");
        fieldType.setSelectors("h1");
        fieldTypes.add(fieldType);
        fieldType.setWebsite(website);

        fieldType = new FieldType();
        fieldType.setCode(FieldType.PRODUCT_CATEGORY);
        fieldType.setName("Product category");
        fieldType.setSelectors(".category");
        fieldTypes.add(fieldType);
        fieldType.setWebsite(website);

        fieldType = new FieldType();
        fieldType.setCode(FieldType.PRODUCT_PRICE);
        fieldType.setName("Product price");
        fieldType.setSelectors(".price");
        fieldTypes.add(fieldType);
        fieldType.setWebsite(website);

        fieldType = new FieldType();
        fieldType.setCode(FieldType.PRODUCT_DESCRIPTION);
        fieldType.setName("Product description");
        fieldType.setSelectors(".description");
        fieldTypes.add(fieldType);
        fieldType.setWebsite(website);

        website.setFieldTypes(fieldTypes);
        Website w = websiteRepository.save(website);

        Website actual = websiteRepository.findById(w.getId()).get();
        Assert.assertNotNull(actual.getPageTypes());

        List<FieldType> actualFieldTypes = fieldTypeRepository.findByWebsiteId(actual.getId());
        Assert.assertFalse(actualFieldTypes.isEmpty());

        List<PageType> actualPageTypes = pageTypeRepository.findByWebsiteId(actual.getId());
        Assert.assertFalse(actualPageTypes.isEmpty());
    }

    @Test
    public void regexTest() {
        String s = "Цена: 100 руб.";
        String d = s.replaceAll("\\D+", "");
        System.out.println(d);
    }
}

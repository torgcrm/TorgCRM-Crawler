package ru.torgcrm.crawler.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.annotation.SessionScope;
import ru.torgcrm.crawler.domain.Crawler;
import ru.torgcrm.crawler.domain.Website;
import ru.torgcrm.crawler.dto.WebsiteDTO;
import ru.torgcrm.crawler.mappers.WebsiteMapper;
import ru.torgcrm.crawler.model.WebsiteModel;
import ru.torgcrm.crawler.repository.CrawlerRepository;
import ru.torgcrm.crawler.repository.WebsiteRepository;

import java.util.Date;
import java.util.List;

@Controller
@SessionScope
public class WebsitesController extends BaseController<WebsiteModel> {

    private static final String listPage = "/websites/websites.xhtml";
    private static final String addPage = "/websites/add.xhtml";
    private static final String pageTypesPage = "/websites/pagetypes.xhtml";
    private static final String fieldTypesPage = "/websites/fieldtypes.xhtml";
    private static final String parsedDataPage = "/websites/parseddata.xhtml";
    private static final String crawlerPage = "/websites/crawler.xhtml";

    @Autowired
    private WebsiteRepository websiteRepository;
    @Autowired
    private CrawlerRepository crawlerRepository;
    private WebsiteMapper websiteMapper;

    public WebsitesController(WebsiteModel websiteModel,
                              WebsiteMapper websiteMapper) {
        this.setModel(websiteModel);
        this.websiteMapper = websiteMapper;
    }

    public void postAddToView() {
        List<WebsiteDTO> websites = websiteMapper.toDto(websiteRepository.findAll());
        getModel().setEntityList(websites);
        getModel().setSelected(null);
    }

    public void postValidate() {
        getModel().setRowSelected(false);
    }

    @Override
    public String onSave() {
        WebsiteDTO websiteDTO = getModel().getEntity();
        Website website = websiteMapper.toEntity(websiteDTO);
        if (website.getId() == null) {
            Crawler crawler = new Crawler();
            crawler.setCron("25 23 * * *"); // every hour
            crawler.setLastCrawlDate(new Date());
            crawlerRepository.save(crawler);

            website.setCrawler(crawler);
        }
        websiteRepository.save(website);
        return listPage;
    }

    @Override
    public String onEdit() {
        getModel().setEntity(getModel().getSelected());
        return addPage;
    }

    @Override
    public String onAdd() {
        getModel().setEntity(new WebsiteDTO());
        return addPage;
    }

    @Override
    public String onDelete() {
        Website website = websiteMapper.toEntity(getModel().getSelected());
        websiteRepository.delete(website);
        getModel().setSelected(null);
        return listPage;
    }

    @Override
    public String onCancel() {
        return listPage;
    }

    public String onCrawlerSettings() {
        return crawlerPage;
    }

    public String onPageTypes() {
        return pageTypesPage;
    }
    public String onFieldTypes() {
        return fieldTypesPage;
    }
    public String onShowParsedData() {
        return parsedDataPage;
    }
}

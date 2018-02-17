package ru.torgcrm.crawler.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import ru.torgcrm.crawler.domain.Crawler;
import ru.torgcrm.crawler.dto.CrawlerDTO;
import ru.torgcrm.crawler.mappers.CrawlerMapper;
import ru.torgcrm.crawler.model.WebsiteCrawlerModel;
import ru.torgcrm.crawler.model.WebsiteModel;
import ru.torgcrm.crawler.repository.CrawlerRepository;

@Component
@SessionScope
public class WebsiteCrawlerController extends BaseController<WebsiteCrawlerModel> {

    private static final String websitesPage = "/websites/websites.xhtml";

    @Autowired
    private WebsiteModel websiteModel;
    @Autowired
    private CrawlerMapper crawlerMapper;
    @Autowired
    private CrawlerRepository crawlerRepository;

    public WebsiteCrawlerController(WebsiteCrawlerModel websiteCrawlerModel) {
        setModel(websiteCrawlerModel);
    }

    public void postAddToView() {
        CrawlerDTO crawlerDTO = crawlerMapper.toDto(websiteModel.getSelected().getCrawler());
        getModel().setEntity(crawlerDTO);
        getModel().setSelected(null);
    }

    public void postValidate() {
        getModel().setRowSelected(false);
    }

    @Override
    public String onSave() {
        Crawler crawler = crawlerMapper.toEntity(getModel().getEntity());
        crawlerRepository.save(crawler);
        return websitesPage;
    }

    @Override
    public String onCancel() {
        return websitesPage;
    }

    @Override
    public String onEdit() {
        return null;
    }

    @Override
    public String onAdd() {
        return null;
    }

    @Override
    public String onDelete() {
        return null;
    }
}

package ru.torgcrm.crawler.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.SessionScope;
import ru.torgcrm.crawler.domain.Website;
import ru.torgcrm.crawler.dto.WebsiteDTO;
import ru.torgcrm.crawler.mappers.WebsiteMapper;
import ru.torgcrm.crawler.model.WebsiteModel;
import ru.torgcrm.crawler.repository.WebsiteRepository;

import java.util.Collections;
import java.util.List;

@Controller
@SessionScope
public class WebsitesController extends BaseController {

    @Autowired
    private WebsiteRepository websiteRepository;
    private WebsiteMapper websiteMapper;

    public WebsitesController(WebsiteModel websiteModel,
                              WebsiteMapper websiteMapper) {
        this.setModel(websiteModel);
        this.websiteMapper = websiteMapper;
    }

    public void loadWebsites() {
        WebsiteModel model = (WebsiteModel) this.getModel();
        List<WebsiteDTO> websites = Collections.EMPTY_LIST;
        model.setWebsites(websites);
    }

    @Override
    public void onSave() {
        WebsiteModel websiteModel = (WebsiteModel) this.getModel();
        WebsiteDTO websiteDTO = websiteModel.getWebsite();
        Website website = websiteMapper.toEntity(websiteDTO);
        websiteRepository.save(website);
    }
}

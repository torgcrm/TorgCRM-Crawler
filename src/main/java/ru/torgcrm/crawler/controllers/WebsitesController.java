package ru.torgcrm.crawler.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.SessionScope;
import ru.torgcrm.crawler.domain.Website;
import ru.torgcrm.crawler.dto.WebsiteDTO;
import ru.torgcrm.crawler.mappers.WebsiteMapper;
import ru.torgcrm.crawler.model.WebsiteModel;
import ru.torgcrm.crawler.repository.WebsiteRepository;

import java.util.List;

@Controller
@SessionScope
public class WebsitesController extends BaseController<WebsiteModel> {

    private static final String listPage = "/websites/websites.xhtml";
    private static final String addPage = "/websites/add.xhtml";
    private static final String pageTypesPage = "/websites/pagetypes.xhtml";
    private static final String fieldTypesPage = "/websites/fieldtypes.xhtml";
    private static final String parsedDataPage = "/websites/parseddata.xhtml";

    @Autowired
    private WebsiteRepository websiteRepository;
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

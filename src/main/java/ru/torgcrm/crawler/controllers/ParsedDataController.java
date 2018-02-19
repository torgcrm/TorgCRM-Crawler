package ru.torgcrm.crawler.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import ru.torgcrm.crawler.domain.Page;
import ru.torgcrm.crawler.domain.PageType;
import ru.torgcrm.crawler.domain.Website;
import ru.torgcrm.crawler.mappers.WebsiteMapper;
import ru.torgcrm.crawler.model.ParsedDataModel;
import ru.torgcrm.crawler.model.WebsiteModel;
import ru.torgcrm.crawler.repository.FieldTypeRepository;
import ru.torgcrm.crawler.repository.PageRepository;
import ru.torgcrm.crawler.repository.PageTypeRepository;
import ru.torgcrm.crawler.repository.WebsiteRepository;

import java.util.*;

@Component
@SessionScope
public class ParsedDataController extends BaseController<ParsedDataModel> {

    @Autowired
    private PageTypeRepository pageTypeRepository;
    @Autowired
    private FieldTypeRepository fieldTypeRepository;
    @Autowired
    private PageRepository pageRepository;
    @Autowired
    private WebsiteModel websiteModel;
    @Autowired
    private WebsiteMapper websiteMapper;
    @Autowired
    private WebsiteRepository websiteRepository;

    public ParsedDataController(ParsedDataModel parsedDataModel) {
        setModel(parsedDataModel);
    }

    @Override
    public void postAddToView() {
        Website website = websiteRepository.findById(websiteModel.getSelected().getId()).get();
        Map<String, Integer> pageTypeCounter = new HashMap<>();
        getModel().setPageTypes(website.getPageTypes());
        for (PageType pageType : website.getPageTypes()) {
            Integer count = pageRepository.countByPageType(pageType);
            pageTypeCounter.put(pageType.getCode(), count);
            getModel().setPageTypeCounter(pageTypeCounter);
        }
    }

    @Override
    public String onSave() {
        return null;
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

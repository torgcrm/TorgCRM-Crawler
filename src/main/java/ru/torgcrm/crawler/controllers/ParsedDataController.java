package ru.torgcrm.crawler.controllers;

import lombok.Getter;
import lombok.Setter;
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

    private static final String pageTypePages = "/websites/pagetype-pages.xhtml";

    @Autowired
    private PageTypeRepository pageTypeRepository;
    @Autowired
    private PageRepository pageRepository;
    @Autowired
    private WebsiteModel websiteModel;

    public ParsedDataController(ParsedDataModel parsedDataModel) {
        setModel(parsedDataModel);
    }

    @Override
    public void postAddToView() {
        List<PageType> pageTypes = pageTypeRepository.findByWebsiteId(websiteModel.getSelected().getId());
        getModel().setPageTypes(pageTypes);
        Map<String, Integer> pageTypeCounter = new HashMap<>();
        for (PageType pageType : pageTypes) {
            Integer count = pageRepository.countByPageType(pageType);
            pageTypeCounter.put(pageType.getCode(), count);
            getModel().setPageTypeCounter(pageTypeCounter);
        }
    }

    public String showPageTypePages(Long typeId) {
        List<Page> pages = pageRepository.findByWebsiteIdAndPageTypeId(websiteModel.getSelected().getId(), typeId);
        getModel().setPagesByPageType(pages);
        return pageTypePages;
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

package ru.torgcrm.crawler.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.annotation.SessionScope;
import ru.torgcrm.crawler.domain.PageType;
import ru.torgcrm.crawler.model.PagesLazyModel;
import ru.torgcrm.crawler.model.ParsedDataModel;
import ru.torgcrm.crawler.model.WebsiteModel;
import ru.torgcrm.crawler.repository.PageRepository;
import ru.torgcrm.crawler.repository.PageTypeRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private PagesLazyModel pagesLazyModel;

    public ParsedDataController(ParsedDataModel parsedDataModel) {
        setModel(parsedDataModel);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
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
//        List<Page> pages = pageRepository.findByWebsiteIdAndPageTypeId(websiteModel.getSelected().getId(), typeId);
        getModel().setPagesLazyModel(pagesLazyModel);
        pagesLazyModel.setPageTypeId(typeId);
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

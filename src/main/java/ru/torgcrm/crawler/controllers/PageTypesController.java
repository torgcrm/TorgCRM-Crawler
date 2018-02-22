package ru.torgcrm.crawler.controllers;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import ru.torgcrm.crawler.domain.PageType;
import ru.torgcrm.crawler.dto.PageTypeDTO;
import ru.torgcrm.crawler.mappers.PageTypeMapper;
import ru.torgcrm.crawler.model.PageTypesModel;
import ru.torgcrm.crawler.model.WebsiteModel;
import ru.torgcrm.crawler.repository.PageTypeRepository;

import java.util.List;

@Component
@SessionScope
public class PageTypesController extends BaseController<PageTypesModel> {

    private static final String addPageTypePage = "/websites/add-pagetype.xhtml";
    private static final String listTypesPage = "/websites/pagetypes.xhtml";

    private PageTypeRepository pageTypeRepository;
    private PageTypeMapper pageTypeMapper;
    private WebsiteModel websiteModel;

    public PageTypesController(PageTypeRepository pageTypeRepository,
                               PageTypesModel pageTypesModel,
                               PageTypeMapper pageTypeMapper,
                               WebsiteModel websiteModel) {
        this.pageTypeRepository = pageTypeRepository;
        this.pageTypeMapper = pageTypeMapper;
        this.setModel(pageTypesModel);
        this.websiteModel = websiteModel;
    }

    public void postAddToView() {
        List<PageType> pageTypes = pageTypeRepository.findByWebsiteIdOrderByIdDesc(websiteModel.getSelected().getId());
        if (pageTypes != null) {
            List<PageTypeDTO> pageTypesDto = pageTypeMapper.toDto(pageTypes);
            getModel().setEntityList(pageTypesDto);
            getModel().setSelected(null);
        }
    }

    public void postValidate() {
        getModel().setRowSelected(false);
    }

    @Override
    public String onSave() {
        pageTypeRepository.save(pageTypeMapper.toEntity(getModel().getEntity()));
        return listTypesPage;
    }

    @Override
    public String onEdit() {
        getModel().setEntity(getModel().getSelected());
        return addPageTypePage;
    }

    @Override
    public String onAdd() {
        getModel().setEntity(new PageTypeDTO());
        return addPageTypePage;
    }

    @Override
    public String onDelete() {
        PageType website = pageTypeMapper.toEntity(getModel().getSelected());
        pageTypeRepository.delete(website);
        getModel().setSelected(null);
        return listTypesPage;
    }

    @Override
    public String onCancel() {
        return listTypesPage;
    }
}

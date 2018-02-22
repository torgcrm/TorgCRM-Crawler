package ru.torgcrm.crawler.controllers;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import ru.torgcrm.crawler.domain.FieldType;
import ru.torgcrm.crawler.domain.PageType;
import ru.torgcrm.crawler.dto.FieldTypeDTO;
import ru.torgcrm.crawler.dto.PageTypeDTO;
import ru.torgcrm.crawler.mappers.FieldTypeMapper;
import ru.torgcrm.crawler.mappers.PageTypeMapper;
import ru.torgcrm.crawler.model.FieldTypesModel;
import ru.torgcrm.crawler.model.WebsiteModel;
import ru.torgcrm.crawler.repository.FieldTypeRepository;
import ru.torgcrm.crawler.repository.PageTypeRepository;

import java.util.List;

@Component
@SessionScope
public class FieldTypesController extends BaseController<FieldTypesModel> {

    private static final String addFieldTypePage = "/websites/add-fieldtype.xhtml";
    private static final String listTypesPage = "/websites/fieldtypes.xhtml";

    private FieldTypeRepository fieldTypeRepository;
    private FieldTypeMapper fieldTypeMapper;
    private PageTypeMapper pageTypeMapper;
    private PageTypeRepository pageTypeRepository;
    private WebsiteModel websiteModel;

    public FieldTypesController(PageTypeRepository pageTypeRepository,
                                FieldTypeRepository fieldTypeRepository,
                                FieldTypesModel fieldTypesModel,
                                FieldTypeMapper fieldTypeMapper,
                                PageTypeMapper pageTypeMapper,
                                WebsiteModel websiteModel) {
        this.pageTypeRepository = pageTypeRepository;
        this.fieldTypeRepository = fieldTypeRepository;
        this.fieldTypeMapper = fieldTypeMapper;
        this.websiteModel = websiteModel;
        this.pageTypeMapper = pageTypeMapper;
        this.setModel(fieldTypesModel);
    }

    public void postAddToView() {
        List<FieldType> fieldTypes = fieldTypeRepository
                .findByWebsiteIdOrderByIdDesc(websiteModel.getSelected().getId());
        if (fieldTypes != null) {
            List<FieldTypeDTO> fieldTypeDtos = fieldTypeMapper.toDto(fieldTypes);
            getModel().setEntityList(fieldTypeDtos);
            getModel().setSelected(null);
            getModel().setEntity(null);
        }
    }

    public void postValidate() {
        getModel().setRowSelected(false);
    }

    @Override
    public String onSave() {
        FieldType fieldType = fieldTypeMapper.toEntity(getModel().getEntity());
        fieldType.setPageType(pageTypeRepository.
                findById(getModel().getSelectedPageTypeId()).get());
        fieldTypeRepository.save(fieldType);
        return listTypesPage;
    }

    @Override
    public String onEdit() {
        initForm();
        getModel().setEntity(getModel().getSelected());
        if (getModel().getSelected().getPageType() != null) {
            getModel().setSelectedPageTypeId(getModel().getSelected().getPageType().getId());
        }

        return addFieldTypePage;
    }

    @Override
    public String onAdd() {
        getModel().setEntity(new FieldTypeDTO());
        getModel().setSelectedPageTypeId(null);
        initForm();
        return addFieldTypePage;
    }

    @Override
    public String onDelete() {
        FieldType fieldType = fieldTypeMapper.toEntity(getModel().getSelected());
        fieldTypeRepository.delete(fieldType);
        getModel().setSelected(null);
        return listTypesPage;
    }

    @Override
    public String onCancel() {
        return listTypesPage;
    }

    public void initForm() {
        List<PageTypeDTO> pageTypes = pageTypeMapper
                .toDto(pageTypeRepository.findByWebsiteId(websiteModel.getSelected().getId()));
        getModel().setPageTypes(pageTypes);
    }
}

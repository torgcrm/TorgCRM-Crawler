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
import ru.torgcrm.crawler.model.PageTypesModel;
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

    public FieldTypesController(FieldTypeRepository pageTypeRepository,
                                FieldTypesModel fieldTypesModel,
                                FieldTypeMapper fieldTypeMapper) {
        this.fieldTypeRepository = pageTypeRepository;
        this.fieldTypeMapper = fieldTypeMapper;
        this.setModel(fieldTypesModel);
    }

    public void postAddToView() {
        List<FieldTypeDTO> pageTypes = fieldTypeMapper.toDto(fieldTypeRepository.findAll());
        getModel().setEntityList(pageTypes);
        getModel().setSelected(null);
    }

    public void postValidate() {
        getModel().setRowSelected(false);
    }

    @Override
    public String onSave() {
        fieldTypeRepository.save(fieldTypeMapper.toEntity(getModel().getEntity()));
        return listTypesPage;
    }

    @Override
    public String onEdit() {
        getModel().setEntity(getModel().getSelected());
        return addFieldTypePage;
    }

    @Override
    public String onAdd() {
        getModel().setEntity(new FieldTypeDTO());
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
}

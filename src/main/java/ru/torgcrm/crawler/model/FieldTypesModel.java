package ru.torgcrm.crawler.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import ru.torgcrm.crawler.domain.PageType;
import ru.torgcrm.crawler.dto.FieldTypeDTO;
import ru.torgcrm.crawler.dto.PageTypeDTO;

import java.util.List;

@Component
@SessionScope
public class FieldTypesModel extends BaseModel<FieldTypeDTO> {
    @Getter
    @Setter
    private List<PageTypeDTO> pageTypes;
    @Getter
    @Setter
    private Long selectedPageTypeId;
}

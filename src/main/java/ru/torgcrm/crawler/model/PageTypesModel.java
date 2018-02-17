package ru.torgcrm.crawler.model;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import ru.torgcrm.crawler.dto.PageTypeDTO;

@Component
@SessionScope
public class PageTypesModel extends BaseModel<PageTypeDTO> {
}

package ru.torgcrm.crawler.model;

import lombok.Getter;
import lombok.Setter;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import ru.torgcrm.crawler.domain.Page;
import ru.torgcrm.crawler.dto.PageDTO;
import ru.torgcrm.crawler.mappers.PageMapper;
import ru.torgcrm.crawler.repository.PageRepository;

import java.util.List;
import java.util.Map;

@Component
@Scope("prototype")
public class PagesLazyModel extends LazyDataModel<PageDTO> {

    private List<PageDTO> dataSource;
    @Autowired
    private PageRepository pageRepository;
    @Autowired
    private PageMapper pageMapper;
    @Getter
    @Setter
    private Long pageTypeId;

    @Override
    public PageDTO getRowData(String rowKey) {
        for (PageDTO page : dataSource) {
            System.out.println(page.getId());
            System.out.println(rowKey);
            if (page.getId().equals(rowKey))
                return page;
        }

        return null;
    }

    @Override
    public Object getRowKey(PageDTO page) {
        return page.getId();
    }

    @Override
    public List<PageDTO> load(int first, int pageSize, String sortField,
                              SortOrder sortOrder, Map<String, Object> filters) {
        List<Page> pages;
        if (filters.isEmpty()) {
            pages = pageRepository.findAllByPageTypeId(
                    pageTypeId, PageRequest.of(first/pageSize, pageSize,
                            new Sort(Sort.Direction.DESC, "id"))
            );
            setRowCount(pageRepository.countByPageTypeId(pageTypeId));

        } else {
            String title = filters.get("title").toString();
            pages = pageRepository.findAllByPageTypeIdAndTitleContaining(
                    pageTypeId, title, PageRequest.of(first/pageSize, pageSize,
                            new Sort(Sort.Direction.DESC, "id"))
            );
            setRowCount(pageRepository.countByPageTypeIdAndTitleContaining(pageTypeId, title));
        }
        this.dataSource = pageMapper.toDto(pages);
        return dataSource;
    }
}

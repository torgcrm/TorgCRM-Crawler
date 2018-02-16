package ru.torgcrm.crawler.mappers;

import ru.torgcrm.crawler.domain.BasicEntity;
import ru.torgcrm.crawler.dto.BaseDTO;

import java.util.List;

public interface BaseMapper<D extends BaseDTO, T extends BasicEntity> {
    T toEntity(D dto);

    D toDto(T entity);

    List<T> toEntity(List<D> dto);

    List<D> toDto(List<T> entity);
}

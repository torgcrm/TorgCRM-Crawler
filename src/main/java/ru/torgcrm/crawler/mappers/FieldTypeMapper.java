package ru.torgcrm.crawler.mappers;

import org.mapstruct.Mapper;
import ru.torgcrm.crawler.domain.FieldType;
import ru.torgcrm.crawler.dto.FieldTypeDTO;

@Mapper(componentModel = "spring")
public interface FieldTypeMapper extends BaseMapper<FieldTypeDTO, FieldType> {
}

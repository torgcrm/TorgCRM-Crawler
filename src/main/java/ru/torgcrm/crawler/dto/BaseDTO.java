package ru.torgcrm.crawler.dto;

import lombok.Getter;
import lombok.Setter;

public class BaseDTO {
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private String code;
    @Getter
    @Setter
    private String name;
}

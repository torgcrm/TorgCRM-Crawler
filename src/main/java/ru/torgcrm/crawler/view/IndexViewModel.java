package ru.torgcrm.crawler.view;

import lombok.Getter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;


@Component
@SessionScope
public class IndexViewModel {
    @Getter
    private String hello = "Hello222";
}

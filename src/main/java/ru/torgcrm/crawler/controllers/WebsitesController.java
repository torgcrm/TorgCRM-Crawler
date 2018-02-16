package ru.torgcrm.crawler.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.SessionScope;
import ru.torgcrm.crawler.dto.WebsiteDTO;
import ru.torgcrm.crawler.model.WebsiteModel;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@SessionScope
public class WebsitesController extends BaseController {
    public WebsitesController(WebsiteModel websiteModel) {
        this.setModel(websiteModel);
    }

    public void loadWebsites() {
        WebsiteModel model = (WebsiteModel) this.getModel();
        List<WebsiteDTO> websites = Stream.generate(WebsiteDTO::new)
                .limit(10).collect(Collectors.toList());
        websites.forEach(website -> {
            website.setId(1L);
            website.setName("Website");
            website.setUrl("http://ya.ru");
        });
        model.setWebsites(websites);
    }
}

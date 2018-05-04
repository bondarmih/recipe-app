package ru.bondarmih.recipeparser.service.parser.impl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import ru.bondarmih.recipeparser.service.parser.DocumentProvider;


/**
 * Created by bondarm on 17.04.18.
 */

@Component
public class WebDocumentProvider implements DocumentProvider {

    @Autowired
    @Qualifier("mainWebClient")
    private WebClient client;

    @Override
    public Document getDocument(String path) {

        WebClient.RequestBodySpec uri = client
                .method(HttpMethod.GET)
                .uri(path);

        String page = uri.retrieve()
                .bodyToMono(String.class)
                .block();

        return Jsoup.parse(page);
    }
}

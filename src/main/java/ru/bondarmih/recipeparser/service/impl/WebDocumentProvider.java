package ru.bondarmih.recipeparser.service.impl;

import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;
import ru.bondarmih.recipeparser.service.DocumentProvider;


/**
 * Created by bondarm on 17.04.18.
 */

@Component
public class WebDocumentProvider implements DocumentProvider {
    @Override
    public Document getDocument() {
        return null;
    }
}

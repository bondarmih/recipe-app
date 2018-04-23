package ru.bondarmih.recipeparser.service.parser;

import org.jsoup.nodes.Document;

/**
 * Created by bondarm on 17.04.18.
 */
public interface DocumentProvider {

    Document getDocument(String path);

}

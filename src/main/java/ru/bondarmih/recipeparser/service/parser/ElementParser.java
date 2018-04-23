package ru.bondarmih.recipeparser.service.parser;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * Created by bondarm on 17.04.18.
 */
public interface ElementParser<T> {

    T parse(Element element);

}

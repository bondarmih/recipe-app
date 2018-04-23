package ru.bondarmih.recipeparser.service.parser.impl;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.select.Selector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.bondarmih.recipeparser.data.domain.RecipeSource;
import ru.bondarmih.recipeparser.service.parser.DocumentProvider;
import ru.bondarmih.recipeparser.service.parser.RecipeSourceParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bondarm on 19.04.18.
 */

@Component
public class RecipeListProcessor {

    @Autowired
    private DocumentProvider documentProvider;

    @Autowired
    private RecipeSourceParser parser;

    public List<RecipeSource> process(String path) {
        List<RecipeSource> sources = new ArrayList<>();
        Document document = documentProvider.getDocument(path);
        Elements recipeElements = Selector.select(".views-item__line_wrap", document.body());
        recipeElements.forEach(element -> {
            sources.add(parser.parse(element));
        });
        return sources;
    }
}

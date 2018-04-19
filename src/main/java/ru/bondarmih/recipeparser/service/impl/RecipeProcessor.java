package ru.bondarmih.recipeparser.service.impl;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Selector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.bondarmih.recipeparser.model.domain.Recipe;
import ru.bondarmih.recipeparser.service.DocumentProvider;
import ru.bondarmih.recipeparser.service.ElementParser;

/**
 * Created by bondarm on 19.04.18.
 */

@Component
public class RecipeProcessor {

    @Autowired
    private DocumentProvider documentProvider;

    @Autowired
    private ElementParser<Recipe> recipeParser;

    public Recipe process(String path) {
        Document document = documentProvider.getDocument(path);
        Element post = Selector.selectFirst(".post",document);
        Recipe recipe = recipeParser.parse(post);

        return recipe;
    }
}

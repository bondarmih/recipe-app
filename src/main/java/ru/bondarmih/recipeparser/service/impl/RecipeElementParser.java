package ru.bondarmih.recipeparser.service.impl;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import ru.bondarmih.recipeparser.model.domain.Recipe;
import ru.bondarmih.recipeparser.service.ElementParser;

/**
 * Created by bondarm on 17.04.18.
 */
public class RecipeElementParser implements ElementParser<Recipe> {
    @Override
    public Recipe parse(Element document) {
        return null;
    }
}

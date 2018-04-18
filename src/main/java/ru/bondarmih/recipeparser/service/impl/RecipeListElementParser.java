package ru.bondarmih.recipeparser.service.impl;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;
import ru.bondarmih.recipeparser.model.domain.RecipeSource;
import ru.bondarmih.recipeparser.service.ElementParser;

import java.util.List;

/**
 * Created by bondarm on 17.04.18.
 */

@Component
public class RecipeListElementParser implements ElementParser<List<RecipeSource>> {
    @Override
    public List<RecipeSource> parse(Element document) {
        return null;
    }
}

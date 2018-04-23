package ru.bondarmih.recipeparser.service.parser.impl;

import org.jsoup.nodes.Element;
import org.jsoup.select.Selector;
import org.springframework.stereotype.Component;
import ru.bondarmih.recipeparser.data.domain.Ingredient;
import ru.bondarmih.recipeparser.service.parser.IngredientParser;

import java.util.Optional;

/**
 * Created by bondarm on 18.04.18.
 */

@Component
public class IngredientParserImpl implements IngredientParser {
    @Override
    public Ingredient parse(Element element) {
        Ingredient ingredient = new Ingredient();
        ingredient.setName(
                Optional.ofNullable(Selector.selectFirst(".name", element))
                        .map(Element::text)
                        .orElse(null)
        );
        ingredient.setNotes(
                Optional.ofNullable(Selector.selectFirst(".notes", element))
                        .map(Element::text)
                        .orElse(null)
        );
        ingredient.setValue(
                Optional.ofNullable(Selector.selectFirst(".value", element))
                        .map(Element::text)
                        .orElse(null)
        );
        ingredient.setUnit(
                Optional.ofNullable(Selector.selectFirst(".type", element))
                        .map(Element::text)
                        .orElse(null)
        );
        return ingredient;
    }
}

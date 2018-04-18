package ru.bondarmih.recipeparser.service.impl;

import org.jsoup.nodes.Element;
import org.jsoup.select.Selector;
import org.springframework.stereotype.Component;
import ru.bondarmih.recipeparser.model.domain.Ingredient;
import ru.bondarmih.recipeparser.service.ElementParser;

import java.util.Optional;

/**
 * Created by bondarm on 18.04.18.
 */

@Component
public class IngredientElementParser implements ElementParser<Ingredient> {
    @Override
    public Ingredient parse(Element element) {
        Ingredient ingredient = new Ingredient();
        ingredient.setName(Selector.selectFirst(".list_label .name", element).text());
        ingredient.setNotes(
                Optional.ofNullable(Selector.selectFirst(".list_label .notes", element))
                        .map(Element::text)
                        .orElse(null)
        );
        ingredient.setValue(
                Optional.ofNullable(Selector.selectFirst(".list_value .value", element))
                        .map(Element::text)
                        .orElse(null)
        );
        ingredient.setUnit(
                Optional.ofNullable(Selector.selectFirst(".list_value .type", element))
                        .map(Element::text)
                        .orElse(null)
        );
        return ingredient;
    }
}

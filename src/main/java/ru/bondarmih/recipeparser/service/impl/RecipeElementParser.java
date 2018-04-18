package ru.bondarmih.recipeparser.service.impl;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Selector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import ru.bondarmih.recipeparser.model.domain.Ingredient;
import ru.bondarmih.recipeparser.model.domain.Recipe;
import ru.bondarmih.recipeparser.service.ElementParser;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by bondarm on 17.04.18.
 */
@Component
public class RecipeElementParser implements ElementParser<Recipe> {

    @Autowired
    private ElementParser<Ingredient> ingredientParser;

    @Override
    public Recipe parse(Element post) {
        Recipe recipe = new Recipe();
        recipe.setName(
                Optional.ofNullable(Selector.selectFirst("h1[itemprop=name]",post))
                        .map(Element::text)
                        .orElse(null)
        );
        recipe.setDescription(
                Optional
                        .ofNullable(Selector.selectFirst(".recipe_desc",post))
                        .map(Element::text)
                        .orElse(null)
        );
        recipe.setIngredients(
                Selector
                        .select(".recipe-ingr>ul>li", post)
                        .stream()
                        .map(e -> ingredientParser.parse(e))
                        .collect(Collectors.toList())
        );
        recipe.setIngredientTags(Selector.select(".ingredient_tags",post).eachText());
        recipe.setInstructions(
                Selector.select("[itemprop=recipeInstructions] .instruction_description",post).eachText()
        );

        recipe.setImgPath(
                Optional.ofNullable(Selector.selectFirst(".recipe-img>img", post))
                    .map(e -> e.attr("src"))
                    .orElse(null)
        );
        recipe.setNotes(Selector.select(".directionsFootnotes>p",post).eachText());
        return recipe;
    }
}

package ru.bondarmih.recipeparser.service.parser.impl;

import org.jsoup.nodes.Element;
import org.jsoup.select.Selector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.bondarmih.recipeparser.data.domain.Ingredient;
import ru.bondarmih.recipeparser.data.domain.Instruction;
import ru.bondarmih.recipeparser.data.domain.Recipe;
import ru.bondarmih.recipeparser.service.parser.ElementParser;
import ru.bondarmih.recipeparser.service.parser.IngredientParser;
import ru.bondarmih.recipeparser.service.parser.InstructionParser;
import ru.bondarmih.recipeparser.service.parser.RecipeParser;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by bondarm on 17.04.18.
 */
@Component
public class RecipeParserImpl implements RecipeParser {

    @Autowired
    private IngredientParser ingredientParser;

    @Autowired
    private InstructionParser instructionParser;

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
                Optional.ofNullable(Selector.selectFirst("[itemprop=recipeInstructions]",post))
                        .map(e -> instructionParser.parse(e))
                        .orElse(null)
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

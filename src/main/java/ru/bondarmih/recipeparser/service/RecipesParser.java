package ru.bondarmih.recipeparser.service;

import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.bondarmih.recipeparser.config.PathsConfiguration;
import ru.bondarmih.recipeparser.model.domain.Recipe;
import ru.bondarmih.recipeparser.model.domain.RecipeSource;
import ru.bondarmih.recipeparser.service.impl.RecipeListProcessor;
import ru.bondarmih.recipeparser.service.impl.RecipeProcessor;

import java.time.Clock;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bondarm on 19.04.18.
 */

@Component
public class RecipesParser {

    @Autowired
    private RecipeListProcessor recipeListProcessor;

    @Autowired
    private RecipeProcessor recipeProcessor;

    @Autowired
    private PathsConfiguration config;

    private List<Recipe> recipes = new ArrayList<>();

    public void parse() {

        Clock clock = Clock.systemUTC();
        long start = clock.millis();
        System.out.println(start);
        for (String path: config.getPaths()) {
            List<RecipeSource> sources = recipeListProcessor.process(path);
            for (RecipeSource source: sources) {
                recipes.add(recipeProcessor.process(source.getPath()));
            }
        }
        System.out.println(recipes.size());
        long end = clock.millis();
        System.out.println(end);
        System.out.println(end - start);
    }
}

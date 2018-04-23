package ru.bondarmih.recipeparser.service.parser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Component;
import ru.bondarmih.recipeparser.config.PathsConfiguration;
import ru.bondarmih.recipeparser.data.domain.Recipe;
import ru.bondarmih.recipeparser.data.repository.RecipeRepository;
import ru.bondarmih.recipeparser.service.parser.impl.RecipeListProcessor;
import ru.bondarmih.recipeparser.service.parser.impl.RecipeProcessor;

import java.time.*;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by bondarm on 19.04.18.
 */

@Component
public class RecipesParser {

    public static final Clock CLOCK = Clock.systemUTC();

    @Autowired
    private RecipeListProcessor recipeListProcessor;

    @Autowired
    private RecipeProcessor recipeProcessor;

    @Autowired
    private PathsConfiguration config;

    @Autowired
    private RecipeRepository recipeRepository;


    public void parse() {
        Instant start = CLOCK.instant();
        System.out.println(String.format("Started at: %s", start.toString()));
        for (String path: config.getPaths()) {
            List<Recipe> recipes = Stream.of(recipeListProcessor.process(path))
                    .flatMap(Collection::stream)
                    .map(source -> recipeProcessor.process(source.getPath()))
                    .peek(recipe -> recipeRepository.save(recipe))
                    .collect(Collectors.toList());
            recipeRepository.saveAll(recipes);
            System.out.println(
                    String.format(
                            "Path %s has been processed, %d recipes persisted",
                            path,
                            recipes.size()
                    )
            );
        }
        Instant end = CLOCK.instant();
        System.out.println(String.format("Started at: %s", end.toString()));
        System.out.println(Duration.between(start, end));
    }
}

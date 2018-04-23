package ru.bondarmih.recipeparser.data.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.bondarmih.recipeparser.data.domain.Recipe;

/**
 * Created by bondarm on 20.04.18.
 */
public interface RecipeRepository extends MongoRepository<Recipe, String> {
}

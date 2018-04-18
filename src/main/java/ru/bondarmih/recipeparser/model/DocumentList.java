package ru.bondarmih.recipeparser.model;

import ru.bondarmih.recipeparser.model.domain.RecipeSource;

import java.util.List;

/**
 * Created by bondarm on 17.04.18.
 */
public class DocumentList {

    private List<RecipeSource> recipeSources;

    public List<RecipeSource> getRecipeSources() {
        return recipeSources;
    }

    public void setRecipeSources(List<RecipeSource> recipeSources) {
        this.recipeSources = recipeSources;
    }
}

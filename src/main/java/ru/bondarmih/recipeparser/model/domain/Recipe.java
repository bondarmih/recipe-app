package ru.bondarmih.recipeparser.model.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

/**
 * Created by bondarm on 18.04.18.
 */

@Document(collection = "recipe")
public class Recipe {
    @Id
    private String name;
    @Field("desc")
    private String description;
    private List<Ingredient> ingredients;
    private List<String> ingredientTags;
    private List<String> instructions;
    private String imgPath;
    private List<String> notes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<String> getIngredientTags() {
        return ingredientTags;
    }

    public void setIngredientTags(List<String> ingredientTags) {
        this.ingredientTags = ingredientTags;
    }

    public List<String> getInstructions() {
        return instructions;
    }

    public void setInstructions(List<String> instructions) {
        this.instructions = instructions;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public List<String> getNotes() {
        return notes;
    }

    public void setNotes(List<String> notes) {
        this.notes = notes;
    }
}

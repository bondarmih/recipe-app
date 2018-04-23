package ru.bondarmih.recipeparser.data.domain;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by bondarm on 17.04.18.
 */

@Document
public class RecipeSource extends BaseEntity {

    private String path;

    private Boolean processed;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Boolean getProcessed() {
        return processed;
    }

    public void setProcessed(Boolean processed) {
        this.processed = processed;
    }
}

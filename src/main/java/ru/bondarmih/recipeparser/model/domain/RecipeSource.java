package ru.bondarmih.recipeparser.model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by bondarm on 17.04.18.
 */

@Entity
@Table(name = "rp_recipe_source")
public class RecipeSource extends BaseEntity {

    @Column(name = "path")
    private String path;

    @Column(name = "processed")
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

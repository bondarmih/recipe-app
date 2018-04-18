package ru.bondarmih.recipeparser.model.domain;

import javax.persistence.*;
import java.util.UUID;

/**
 * Created by bondarm on 17.04.18.
 */

@MappedSuperclass
public class BaseEntity {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected UUID id;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}

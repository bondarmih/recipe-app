package ru.bondarmih.recipeparser.data.repository;

import org.springframework.data.repository.CrudRepository;
import ru.bondarmih.recipeparser.data.domain.ImageDescriptor;

/**
 * Created by bondarm on 21.04.18.
 */
public interface ImagesRepository extends CrudRepository<ImageDescriptor, Long> {
}

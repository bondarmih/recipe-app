package ru.bondarmih.recipeparser.service.impl;

import org.jsoup.nodes.Element;
import org.jsoup.select.Selector;
import org.springframework.stereotype.Component;
import ru.bondarmih.recipeparser.model.domain.RecipeSource;
import ru.bondarmih.recipeparser.service.ElementParser;

/**
 * Created by bondarm on 17.04.18.
 */

@Component
public class RecipeListElementParser implements ElementParser<RecipeSource> {
    @Override
    public RecipeSource parse(Element element) {
        String path = Selector.selectFirst(".views-item__line_content>h3>a", element)
                .attr("href");
        RecipeSource source = new RecipeSource();
        source.setPath(path);
        source.setProcessed(false);
        return source;
    }
}

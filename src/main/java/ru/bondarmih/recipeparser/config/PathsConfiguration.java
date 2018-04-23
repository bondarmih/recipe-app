package ru.bondarmih.recipeparser.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bondarm on 20.04.18.
 */

@Configuration
@ConfigurationProperties("recipelist")
public class PathsConfiguration {
    //TODO move to properties
    private Integer[] broken = {103, 218};
    public static final int FIRST_INDEXED_PAGE = 407;
    private Integer lastPage;
    private String basePath;

    public List<String> getPaths() {
        List<String> paths = new ArrayList<>();
        paths.add(basePath);
        if (lastPage == null || lastPage <= FIRST_INDEXED_PAGE) {
            return paths;
        }
        for (int i = FIRST_INDEXED_PAGE; i <= lastPage; i++) {
            paths.add(basePath + String.format("/page/%d/", i));
        }
        return paths;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public void setLastPage(Integer lastPage) {
        this.lastPage = lastPage;
    }
}

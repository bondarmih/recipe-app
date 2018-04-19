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

    public static final int LAST_PAGE = 10;
    String basePath;

    public List<String> getPaths() {
        List<String> paths = new ArrayList<>();
        paths.add(basePath);
        for (int i = 2; i <= LAST_PAGE; i++) {
            paths.add(basePath + String.format("/page/%d/", i));
        }
        return paths;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }
}

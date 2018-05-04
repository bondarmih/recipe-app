package ru.bondarmih.recipeparser.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.concurrent.Executor;

/**
 * Created by bondarm on 27.04.18.
 */

@Configuration
public class AppConfiguration {

    @Value("${recipelist.basePath}")
    private String basePath;

    @Bean
    public WebClient mainWebClient() {
        return WebClient.builder()
                .baseUrl(basePath)
                .build();
    }

    @Bean
    public ExitCodeGenerator exitCodeGenerator() {
        return () -> 1;
    }

    @Bean
    public Executor threadPoolExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(50);
        return executor;
    }
}

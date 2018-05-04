package ru.bondarmih.recipeparser;

import io.netty.util.concurrent.RejectedExecutionHandlers;
import io.netty.util.concurrent.ThreadPerTaskExecutor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.reactive.function.client.WebClient;
import ru.bondarmih.recipeparser.service.parser.RecipesParser;
import sun.nio.ch.ThreadPool;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@SpringBootApplication
public class RecipeParserApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx =
				SpringApplication.run(RecipeParserApplication.class, args);

		RecipesParser parser = ctx.getBean(RecipesParser.class);
		parser.parse();

		ExitCodeGenerator exitCodeGenerator = ctx.getBean(ExitCodeGenerator.class);
		System.exit(SpringApplication.exit(ctx, exitCodeGenerator));
	}
}
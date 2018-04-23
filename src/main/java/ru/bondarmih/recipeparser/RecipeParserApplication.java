package ru.bondarmih.recipeparser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.bondarmih.recipeparser.service.parser.RecipesParser;

@SpringBootApplication
public class RecipeParserApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx =
				SpringApplication.run(RecipeParserApplication.class, args);

		RecipesParser parser = ctx.getBean(RecipesParser.class);
		parser.parse();
	}
}

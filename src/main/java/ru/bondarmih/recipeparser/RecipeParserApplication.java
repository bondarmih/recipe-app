package ru.bondarmih.recipeparser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Selector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.reactive.function.client.WebClient;
import ru.bondarmih.recipeparser.model.domain.Ingredient;
import ru.bondarmih.recipeparser.model.domain.Recipe;
import ru.bondarmih.recipeparser.service.impl.RecipeElementParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@SpringBootApplication
public class RecipeParserApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx =
				SpringApplication.run(RecipeParserApplication.class, args);

		WebClient client = WebClient.builder()
				.baseUrl("http://vkuso.ru")
				.build();

		WebClient.RequestBodySpec uri = client
				.method(HttpMethod.GET)
				.uri("recipe/zapechennye-kabachki-s-syrom-v-smetannom-souse/");

		String page = uri.retrieve()
				.bodyToMono(String.class)
				.block();

		Document document = Jsoup.parse(page);
		Element post = Selector.selectFirst(".post",document);
		RecipeElementParser parser = ctx.getBean(RecipeElementParser.class);
		Recipe recipe = parser.parse(post);
	}
}

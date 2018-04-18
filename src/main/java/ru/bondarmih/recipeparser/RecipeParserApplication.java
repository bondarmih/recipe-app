package ru.bondarmih.recipeparser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Selector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.reactive.function.client.WebClient;
import ru.bondarmih.recipeparser.model.domain.Ingredient;

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
		String name = Optional.ofNullable(Selector.selectFirst("h1[itemprop=name]",post))
				.map(Element::text)
				.orElse(null);
		String recipeDescr = Optional
				.ofNullable(Selector.selectFirst(".recipe_desc",post))
				.map(Element::text)
				.orElse(null);

		List<Ingredient> ingredients = Selector
				.select(".recipe-ingr>ul>li", post)
				.stream()
				.map(element -> {
					Ingredient ingredient = new Ingredient();
					ingredient.setName(Selector.selectFirst(".list_label .name", element).text());
					ingredient.setNotes(
							Optional.ofNullable(Selector.selectFirst(".list_label .notes", element))
									.map(Element::text)
									.orElse(null)
					);
					ingredient.setValue(
							Optional.ofNullable(Selector.selectFirst(".list_value .value", element))
									.map(Element::text)
									.orElse(null)
					);
					ingredient.setUnit(
							Optional.ofNullable(Selector.selectFirst(".list_value .type", element))
									.map(Element::text)
									.orElse(null)
							);
					return ingredient;
				}).collect(Collectors.toList());

		List<String> ingredientTags = Selector
				.select(
					".ingredient_tags",
					post
				)
				.eachText();

		List<String> instructions = Selector
				.select(
						"[itemprop=recipeInstructions] .instruction_description",
						post
				)
				.eachText();

		String imgPath = Selector.selectFirst(".recipe-img>img", post).attr("src");

		byte[] img = WebClient.create(imgPath).method(HttpMethod.GET).retrieve().bodyToMono(byte[].class).block();

		List<String> recipeNotes = Selector
				.select(
						".directionsFootnotes>p",
						post
				)
				.eachText();
	}
}

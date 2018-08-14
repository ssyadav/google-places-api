package se.walkercrou.places.processor;

import java.util.List;

public interface Scraper {

	List<Hospital> scrapeProducts(String url);
//	Result scrapeProductsByQuery(String query);
//	Result getCategories();
	List<Hospital> getFaqs(String url);
}


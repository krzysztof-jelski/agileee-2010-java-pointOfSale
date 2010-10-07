package ca.jbrains.pos;

import java.util.HashMap;
import java.util.Map;

public class Catalog {
	private final Map<String, String> catalogAsMap;

	public Catalog() {
		this.catalogAsMap = new HashMap<String, String>();
	}

	public boolean priceExists(String code) {
		return catalogAsMap.containsKey(code);
	}

	public String getPriceAsString(String code) {
		return catalogAsMap.get(code);
	}

	private void add(String code, String price) {
		catalogAsMap.put(code, price);
	}

	public static Catalog with(String code, String priceAsString) {
		Catalog catalog = new Catalog();
		catalog.add(code, priceAsString);
		return catalog;
	}

	public CatalogItem findItem(String code) {
		return new CatalogItem(getPriceAsString(code));
	}

}
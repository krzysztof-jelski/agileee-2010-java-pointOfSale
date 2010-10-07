package ca.jbrains.pos;

import java.util.HashMap;
import java.util.Map;

public class Catalog {
	private Map<String, CatalogItem> itemsByCode;

	public Catalog() {
		this.itemsByCode = new HashMap<String, CatalogItem>();
	}

	public boolean priceExists(String code) {
		return itemsByCode.containsKey(code);
	}

	public CatalogItem findItem(String code) {
		return itemsByCode.get(code);
	}

	public static Catalog with(String code, int price) {
		Catalog catalog = new Catalog();
		catalog.add(code, price);
		return catalog;
	}

	private void add(String code, int price) {
		itemsByCode.put(code, new CatalogItem(price));
	}

	int findCostFor(String code) {
		return findItem(code).cost();
	}

}
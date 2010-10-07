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

	private void add(String code, String price) {
		itemsByCode.put(code, new CatalogItem(price));
	}

	public static Catalog with(String code, String priceAsString) {
		Catalog catalog = new Catalog();
		catalog.add(code, priceAsString);
		return catalog;
	}

	public CatalogItem findItem(String code) {
		return itemsByCode.get(code);
	}

}
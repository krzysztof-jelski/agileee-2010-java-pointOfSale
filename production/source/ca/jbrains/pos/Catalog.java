package ca.jbrains.pos;

import java.util.HashMap;
import java.util.Map;

public class Catalog {
	private Map<String, CatalogItem> itemsByCode;
	private Taxes taxes;

	public Catalog() {
		this.itemsByCode = new HashMap<String, CatalogItem>();
		this.taxes = new Taxes();
	}

	public Catalog(Taxes taxes) {
		this();
		this.taxes = taxes;
	}

	public boolean priceExists(String code) {
		return itemsByCode.containsKey(code);
	}

	private CatalogItem findItem(String code) {
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

	public int findCostFor(String code) {
		return findItem(code).cost(taxes);
	}

	public void add(String code, int price, boolean pstExempt) {
		add(code, price);
	}

}
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

	public String getPrice(String code) {
		return catalogAsMap.get(code);
	}

	public void add(String code, String price) {
		catalogAsMap.put(code, price);
	}

}
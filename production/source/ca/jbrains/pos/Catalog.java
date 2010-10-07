package ca.jbrains.pos;

import java.util.Collections;
import java.util.Map;

public class Catalog {
	private Map<String, CatalogItem> itemsByCode;

	public Catalog(Map<String, CatalogItem> itemsByCode) {
		this.itemsByCode = itemsByCode;
	}

	public boolean contains(String code) {
		return itemsByCode.containsKey(code);
	}

	public CatalogItem findItem(String code) {
		return itemsByCode.get(code);
	}

	public static Catalog with(CatalogItem catalogItem) {
		return new Catalog(Collections.singletonMap(catalogItem.barcode, catalogItem));
	}

}
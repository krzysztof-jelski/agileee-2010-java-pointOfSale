package ca.jbrains.pos;

import java.util.HashMap;
import java.util.Map;

public class Catalog {
	private Map<String, CatalogItem> itemsByCode;
	private Taxes taxes;

	public Catalog() {
		this(Taxes.withGstAndPst(0, 0));
	}

	public Catalog(Taxes taxes) {
		this.taxes = taxes;
		this.itemsByCode = new HashMap<String, CatalogItem>();
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
		CatalogItem item = findItem(code);
		return addSalesTaxesTo(item);
	}

	public int addSalesTaxesTo(CatalogItem item) {
		int result = item.price + taxes.gstTax(item.price);
		if (!item.pstExempt)
			result = result + taxes.pstTax(result);
		return result;
	}

	public void add(String code, int price, boolean pstExempt) {
		itemsByCode.put(code, new CatalogItem(price, pstExempt));
	}

}
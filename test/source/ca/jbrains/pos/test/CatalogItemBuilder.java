package ca.jbrains.pos.test;

import ca.jbrains.pos.CatalogItem;

public class CatalogItemBuilder {

	private String barcode;
	private int price;
	private boolean pstExempt;

	public CatalogItem build() {
		return new CatalogItem(barcode, price, pstExempt);
	}

	public static CatalogItem aCatalogItem() {
		return new CatalogItemBuilder().build();
	}

	public CatalogItemBuilder withCode(String barCode) {
		this.barcode = barCode;
		return this;
	}

	public CatalogItemBuilder withPrice(int price) {
		this.price = price;
		return this;
	}

	public CatalogItemBuilder pstExempt(boolean pstExempt) {
		this.pstExempt = pstExempt;
		return this;
	}

}

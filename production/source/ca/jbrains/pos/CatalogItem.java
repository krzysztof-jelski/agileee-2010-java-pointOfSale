package ca.jbrains.pos;

public class CatalogItem {

	public final int price;

	public CatalogItem(int price) {
		this.price = price;
	}

	public int cost(Taxes taxes) {
		return price + taxes.gstTax(price);
	}

}

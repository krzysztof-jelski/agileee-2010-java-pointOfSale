package ca.jbrains.pos;

public class CatalogItem {

	public final int price;
	final boolean pstExempt;

	public CatalogItem(int price) {
		this(price, true);
	}

	public CatalogItem(int price, boolean pstExempt) {
		this.price = price;
		this.pstExempt = pstExempt;
	}

}

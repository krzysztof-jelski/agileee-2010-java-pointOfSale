package ca.jbrains.pos;

public class CatalogItem {

	public final int price;
	private final boolean pstExempt;

	public CatalogItem(int price) {
		this(price, true);
	}

	public CatalogItem(int price, boolean pstExempt) {
		this.price = price;
		this.pstExempt = pstExempt;
	}

	public int cost(Taxes taxes) {
		int result = price + taxes.gstTax(price);
		if (!pstExempt)
			result = result + taxes.pstTax(result);
		return result;
	}

}

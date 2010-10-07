package ca.jbrains.pos;

public class CatalogItem {

	public final int price;
	final boolean pstExempt;
	public final String barcode;

	public CatalogItem(int price, boolean pstExempt) {
		this(null, price, pstExempt);
	}

	public CatalogItem(String barcode, int price, boolean pstExempt) {
		this.barcode = barcode;
		this.price = price;
		this.pstExempt = pstExempt;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((barcode == null) ? 0 : barcode.hashCode());
		result = prime * result + price;
		result = prime * result + (pstExempt ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CatalogItem other = (CatalogItem) obj;
		if (barcode == null) {
			if (other.barcode != null)
				return false;
		} else if (!barcode.equals(other.barcode))
			return false;
		if (price != other.price)
			return false;
		if (pstExempt != other.pstExempt)
			return false;
		return true;
	}

}

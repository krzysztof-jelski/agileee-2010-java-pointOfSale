package ca.jbrains.pos;

public class Main {

	public static void main(String[] args) {
		SaleTerminalListener saleTerminalListener =
				new SaleTerminalListener(
						new Screen(System.out),
						new PointOfSaleWithCatalog(
								Catalog.with(new CatalogItem("123", 10000, false)),
								Taxes.withGstAndPst(5, 10)));
		saleTerminalListener.onBarcode("123");
		saleTerminalListener.onBarcode("000");
		saleTerminalListener.onBarcode("");
	}

}

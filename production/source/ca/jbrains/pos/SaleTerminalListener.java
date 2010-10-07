package ca.jbrains.pos;

public class SaleTerminalListener {
	private final Display display;

	private final Catalog catalog;

	private final PointOfSale pointOfSale;

	public SaleTerminalListener(Display display, Catalog catalog, PointOfSale pointOfSale) {
		this.display = display;
		this.catalog = catalog;
		this.pointOfSale = pointOfSale;
	}

	public void onBarcode(String code) {
		boolean useNewAlgorithm = pointOfSale != null;
		if (useNewAlgorithm) {
			SaleResult sale = pointOfSale.tryToSell(code);
			display.displayPrice(sale.price);
		} else {
			if (catalog.priceExists(code)) {
				display.displayPrice(catalog.findCostFor(code));
			} else if (code.isEmpty()) {
				display.displayScannedEmptyBarcode();
			} else {
				display.displayNoProductFound(code);
			}
		}
	}
}
package ca.jbrains.pos;

public class PointOfSale {
	private final Screen screen;

	private final Catalog catalog;

	public PointOfSale(Screen screen, Catalog catalog) {
		this.screen = screen;
		this.catalog = catalog;
	}

	public void onBarcode(String code) {
		if (catalog.priceExists(code)) {
			screen.displayPrice(catalog.findCostFor(code));
		} else if (code.isEmpty()) {
			screen.displayScannedEmptyBarcode();
		} else {
			screen.displayNoProductFound(code);
		}
	}
}
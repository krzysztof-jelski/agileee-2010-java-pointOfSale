package ca.jbrains.pos;

public class SaleTerminalListener {
	private final Display display;

	private final Catalog catalog;

	public SaleTerminalListener(Display display, Catalog catalog) {
		this.display = display;
		this.catalog = catalog;
	}

	public void onBarcode(String code) {
		if (catalog.priceExists(code)) {
			display.displayPrice(catalog.findCostFor(code));
		} else if (code.isEmpty()) {
			display.displayScannedEmptyBarcode();
		} else {
			display.displayNoProductFound(code);
		}
	}
}
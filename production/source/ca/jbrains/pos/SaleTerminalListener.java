package ca.jbrains.pos;

public class SaleTerminalListener {
	private final Display display;

	private final PointOfSale pointOfSale;

	public SaleTerminalListener(Display display, PointOfSale pointOfSale) {
		this.display = display;
		this.pointOfSale = pointOfSale;
	}

	public void onBarcode(String code) {
		pointOfSale.tryToSell(code).renderOn(display);
	}
}
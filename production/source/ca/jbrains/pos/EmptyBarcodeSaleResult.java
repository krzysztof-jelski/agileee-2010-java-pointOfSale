package ca.jbrains.pos;

public class EmptyBarcodeSaleResult implements SaleResult {

	@Override
	public void renderOn(Display display) {
		display.displayScannedEmptyBarcode();
	}

}

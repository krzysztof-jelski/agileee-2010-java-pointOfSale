package ca.jbrains.pos;

public class Screen {

	// not tested
	private void display(String text) {
		System.out.println(text);
	}

	public void displayScannedEmptyBarcode() {
		display("scanned empty barcode");
	}

	public void displayNoProductFound(String code) {
		display(String.format("no product found for barcode: %s", code));
	}

	public void displayPrice(String price) {
		display(price);
	}
}
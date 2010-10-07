package ca.jbrains.pos;

public interface Display {

	void displayScannedEmptyBarcode();

	void displayNoProductFound(String code);

	void displayPrice(int price);

}
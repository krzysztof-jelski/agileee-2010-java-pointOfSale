package ca.jbrains.pos;

import java.io.PrintStream;
import java.text.NumberFormat;
import java.util.Locale;

public class Screen implements Display {

	private PrintStream printStream;

	public Screen(PrintStream printStream) {
		this.printStream = printStream;
	}

	private void display(String text) {
		printStream.println(text);
	}

	@Override
	public void displayScannedEmptyBarcode() {
		display("scanned empty barcode");
	}

	@Override
	public void displayNoProductFound(String code) {
		display(String.format("no product found for barcode: %s", code));
	}

	@Override
	public void displayPrice(int price) {
		display(NumberFormat.getCurrencyInstance(Locale.US).format(((double) price / 100)));
	}
}
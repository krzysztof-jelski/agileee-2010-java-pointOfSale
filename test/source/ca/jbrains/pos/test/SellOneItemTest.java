package ca.jbrains.pos.test;

import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class SellOneItemTest {

	static class PointOfSale {

		private final Screen screen;

		private final Map<String, String> catalog;

		public PointOfSale(Screen screen, Map<String, String> catalog) {
			this.screen = screen;
			this.catalog = catalog;
		}

		public void onBarcode(String code) {
			if (priceExists(code)) {
				screen.displayPrice(getPrice(code));				
			} else if (code.isEmpty()) {
				screen.displayScannedEmptyBarcode();
			} else {
				screen.displayNoProductFound(code);
			}
		}

		private boolean priceExists(String code) {
			return catalog.containsKey(code);
		}

		private String getPrice(String code) {
			return catalog.get(code);
		}
	}

	static class Screen {
		private String text;

		public String getText() {
			return text;
		}

		private void display(String text) {
			this.text = text;
		}

		void displayScannedEmptyBarcode() {
			display("scanned empty barcode");
		}

		void displayNoProductFound(String code) {
			display(String.format("no product found for barcode: %s", code));
		}

		void displayPrice(String price) {
			display(price);
		}
	}

	private final Map<String, String> catalog = new HashMap<String, String>();
	
	private Screen screen = new Screen();
	
	private PointOfSale pointOfSale = new PointOfSale(screen, catalog);
	
	@Before
	public void setUp() {
		catalog.put("firstBarCode", "$123.50");
		catalog.put("anotherBarCode", "$256.50");
	}

	@Test
	public void productFound() throws Exception {
		pointOfSale.onBarcode("firstBarCode");
		
		Assert.assertEquals("$123.50", screen.getText());
	}

	@Test
	public void productFoundForAnotherBarcode() throws Exception {
		pointOfSale.onBarcode("anotherBarCode");
		
		Assert.assertEquals("$256.50", screen.getText());
	}
	
	@Test
	public void noProductFound() throws Exception {
		pointOfSale.onBarcode("unknown barCode");
		
		Assert.assertEquals("no product found for barcode: unknown barCode", screen.getText());
	}

	@Test
	public void emptyBarcodeReceived() throws Exception {
		pointOfSale.onBarcode("");
		
		Assert.assertEquals("scanned empty barcode", screen.getText());
	}
}
package ca.jbrains.pos.test;

import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Test;

public class SellOneItemTest {

	private static class Barcode {
		private final String code;
		
		Barcode(String code) {
			this.code = code;
		}
		
		public String getCode() {
			return code;
		}
	}
	
	public static class Catalog {

		private final Map<Barcode, String> catalogAsMap;

		public Catalog() {
			this.catalogAsMap = new HashMap<Barcode, String>();
		}

		boolean priceExists(String code) {
			return catalogAsMap.containsKey(code);
		}

		String getPrice(String code) {
			return catalogAsMap.get(code);
		}

		void add(String code, String price) {
			catalogAsMap.put(new Barcode(code), price);
		}

	}

	static class PointOfSale {

		private final Screen screen;

		private final Catalog catalog;

		public PointOfSale(Screen screen, Catalog catalog) {
			this.screen = screen;
			this.catalog = catalog;
		}

		public void onBarcode(String code) {
			if (catalog.priceExists(code)) {
				screen.displayPrice(catalog.getPrice(code));
			} else if (code.isEmpty()) {
				screen.displayScannedEmptyBarcode();
			} else {
				screen.displayNoProductFound(code);
			}
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

	private Screen screen = new Screen();

	private Catalog catalog = new Catalog();

	private PointOfSale pointOfSale = new PointOfSale(screen, catalog);

	@Test
	public void productFound() throws Exception {
		catalog.add("firstBarCode", "$123.50");

		pointOfSale.onBarcode("firstBarCode");

		Assert.assertEquals("$123.50", screen.getText());
	}

	@Test
	public void productFoundForAnotherBarcode() throws Exception {
		catalog.add("anotherBarCode", "$256.50");

		pointOfSale.onBarcode("anotherBarCode");

		Assert.assertEquals("$256.50", screen.getText());
	}

	@Test
	public void noProductFound() throws Exception {
		pointOfSale.onBarcode("unknown barCode");

		Assert.assertEquals("no product found for barcode: unknown barCode",
				screen.getText());
	}

	@Test
	public void emptyBarcodeReceived() throws Exception {
		pointOfSale.onBarcode("");

		Assert.assertEquals("scanned empty barcode", screen.getText());
	}
}
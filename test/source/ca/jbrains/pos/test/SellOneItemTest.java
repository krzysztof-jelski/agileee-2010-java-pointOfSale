package ca.jbrains.pos.test;

import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Test;

public class SellOneItemTest {
	

	private static class PointOfSale {
		private static final String NO_PRODUCT_FOUND_MESSAGE = "no product found for barcode: %s";
		private final Screen screen;

		private Map<String, String> storage = new HashMap<String, String>();

		{
			storage.put("firstBarCode", "$123.50");
			storage.put("anotherBarCode", "$256.50");
		}

		public PointOfSale(Screen screen) {
			this.screen = screen;
		}

		public void onBarcode(String code) {
			if (storage.containsKey(code)) {
				screen.display(storage.get(code));				
			} else if (code.isEmpty()) {
				screen.display("scanned empty barcode");
			} else {
				screen.display(noProductFoundMessage(code));
			}
		}

		private String noProductFoundMessage(String code) {
			return String.format(NO_PRODUCT_FOUND_MESSAGE, code);
		}
	}

	private static class Screen {
		private String price;

		public String getText() {
			return price;
		}

		public void display(String price) {
			this.price = price;
		}
	}

	private Screen screen = new Screen();
	private PointOfSale pointOfSale = new PointOfSale(screen);

	@Test
	public void productFound() throws Exception {
		String code = "firstBarCode";
		pointOfSale.onBarcode(code);
		String price = screen.getText();
		Assert.assertEquals("$123.50", price);
	}

	@Test
	public void productFoundForAnotherBarcode() throws Exception {
		String code = "anotherBarCode";
		pointOfSale.onBarcode(code);
		String price = screen.getText();
		Assert.assertEquals("$256.50", price);
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
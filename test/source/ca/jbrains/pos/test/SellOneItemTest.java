package ca.jbrains.pos.test;

import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Test;

public class SellOneItemTest {
	
	private static class PointOfSale {
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
			screen.display(storage.get(code));
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

}
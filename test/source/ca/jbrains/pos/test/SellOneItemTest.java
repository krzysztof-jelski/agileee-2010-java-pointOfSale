package ca.jbrains.pos.test;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Test;


public class SellOneItemTest {
	private static class PointOfSale {
		public void onBarcode(String code) {
		}
	}

	private static class Screen {
		public String getText() {
			return "$123.50";
		}
	}

	private Screen screen = new Screen();

	@Test
	public void productFound() throws Exception {
		String code = "randomCode";
		new PointOfSale().onBarcode(code);
		String price = screen.getText();
		Assert.assertEquals("$123.50", price);
	}
}

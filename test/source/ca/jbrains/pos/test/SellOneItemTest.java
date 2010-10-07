package ca.jbrains.pos.test;

import junit.framework.Assert;

import org.junit.Test;

import ca.jbrains.pos.Catalog;
import ca.jbrains.pos.PointOfSale;
import ca.jbrains.pos.Screen;

public class SellOneItemTest {
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
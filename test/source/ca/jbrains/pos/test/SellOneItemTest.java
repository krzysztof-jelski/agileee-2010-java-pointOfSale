package ca.jbrains.pos.test;

import static org.mockito.Mockito.*;

import org.junit.Test;

import ca.jbrains.pos.Catalog;
import ca.jbrains.pos.PointOfSale;
import ca.jbrains.pos.Screen;

public class SellOneItemTest {
	private Screen screen = mock(Screen.class);

	private Catalog catalog = new Catalog();

	private PointOfSale pointOfSale = new PointOfSale(screen, catalog);

	@Test
	public void productFound() throws Exception {
		PointOfSale pointOfSale = new PointOfSale(screen, Catalog.with("firstBarCode", 12350));

		pointOfSale.onBarcode("firstBarCode");

		verify(screen).displayPrice(12350);
	}

	@Test
	public void productFoundForAnotherBarcode() throws Exception {
		PointOfSale pointOfSale = new PointOfSale(screen, Catalog.with("anotherBarCode", 25650));

		pointOfSale.onBarcode("anotherBarCode");

		verify(screen).displayPrice(25650);
	}

	@Test
	public void noProductFound() throws Exception {
		PointOfSale pointOfSale = new PointOfSale(screen, createCatalogWithout("unknown barCode"));

		pointOfSale.onBarcode("unknown barCode");

		verify(screen).displayNoProductFound("unknown barCode");
	}

	@Test
	public void emptyBarcodeReceived() throws Exception {
		pointOfSale.onBarcode("");

		verify(screen).displayScannedEmptyBarcode();
	}

	private Catalog createCatalogWithout(@SuppressWarnings("unused") String barcode) {
		return new Catalog();
	}
}

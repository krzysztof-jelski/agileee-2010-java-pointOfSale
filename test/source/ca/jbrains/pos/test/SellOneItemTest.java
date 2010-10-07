package ca.jbrains.pos.test;

import static org.mockito.Mockito.*;

import org.junit.Test;

import ca.jbrains.pos.Catalog;
import ca.jbrains.pos.SaleTerminalListener;
import ca.jbrains.pos.Screen;

public class SellOneItemTest {
	private Screen screen = mock(Screen.class);

	private Catalog catalog = new Catalog();

	private SaleTerminalListener saleTerminalListener = new SaleTerminalListener(screen, catalog);

	@Test
	public void productFound() throws Exception {
		SaleTerminalListener saleTerminalListener = new SaleTerminalListener(screen, Catalog.with("firstBarCode", 12350));

		saleTerminalListener.onBarcode("firstBarCode");

		verify(screen).displayPrice(12350);
	}

	@Test
	public void productFoundForAnotherBarcode() throws Exception {
		SaleTerminalListener saleTerminalListener = new SaleTerminalListener(screen, Catalog.with("anotherBarCode", 25650));

		saleTerminalListener.onBarcode("anotherBarCode");

		verify(screen).displayPrice(25650);
	}

	@Test
	public void noProductFound() throws Exception {
		SaleTerminalListener saleTerminalListener = new SaleTerminalListener(screen, createCatalogWithout("unknown barCode"));

		saleTerminalListener.onBarcode("unknown barCode");

		verify(screen).displayNoProductFound("unknown barCode");
	}

	@Test
	public void emptyBarcodeReceived() throws Exception {
		saleTerminalListener.onBarcode("");

		verify(screen).displayScannedEmptyBarcode();
	}

	private Catalog createCatalogWithout(@SuppressWarnings("unused") String barcode) {
		return new Catalog();
	}
}

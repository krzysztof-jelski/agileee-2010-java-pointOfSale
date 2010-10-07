package ca.jbrains.pos.test;

import static org.mockito.Mockito.*;

import org.junit.Test;

import ca.jbrains.pos.Catalog;
import ca.jbrains.pos.Display;
import ca.jbrains.pos.PointOfSale;
import ca.jbrains.pos.SaleResult;
import ca.jbrains.pos.SaleTerminalListener;

public class SellOneItemTest {
	private Display display = mock(Display.class);

	private Catalog catalog = new Catalog();

	private SaleTerminalListener saleTerminalListener = saleTerminalListenerWith(catalog);

	@Test
	public void productFound() throws Exception {
		PointOfSale pointOfSale = mock(PointOfSale.class);
		when(pointOfSale.tryToSell("firstBarCode")).thenReturn(new SaleResult(12350));
		SaleTerminalListener saleTerminalListener = new SaleTerminalListener(display, null, pointOfSale);

		saleTerminalListener.onBarcode("firstBarCode");

		verify(display).displayPrice(12350);
	}

	@Test
	public void productFoundForAnotherBarcode() throws Exception {
		SaleTerminalListener saleTerminalListener = saleTerminalListenerWith(Catalog.with("anotherBarCode", 25650));

		saleTerminalListener.onBarcode("anotherBarCode");

		verify(display).displayPrice(25650);
	}

	@Test
	public void noProductFound() throws Exception {
		SaleTerminalListener saleTerminalListener = saleTerminalListenerWith(createCatalogWithout("unknown barCode"));

		saleTerminalListener.onBarcode("unknown barCode");

		verify(display).displayNoProductFound("unknown barCode");
	}

	@Test
	public void emptyBarcodeReceived() throws Exception {
		saleTerminalListener.onBarcode("");

		verify(display).displayScannedEmptyBarcode();
	}

	private Catalog createCatalogWithout(@SuppressWarnings("unused") String barcode) {
		return new Catalog();
	}

	private SaleTerminalListener saleTerminalListenerWith(Catalog catalog) {
		PointOfSale pointOfSale = mock(PointOfSale.class);
		return new SaleTerminalListener(display, catalog, null);
	}

}

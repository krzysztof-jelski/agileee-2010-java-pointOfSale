package ca.jbrains.pos.test;

import static org.mockito.Mockito.*;

import org.junit.Test;

import ca.jbrains.pos.Display;
import ca.jbrains.pos.EmptyBarcodeSaleResult;
import ca.jbrains.pos.PointOfSale;
import ca.jbrains.pos.SaleTerminalListener;
import ca.jbrains.pos.SuccessfulSaleResult;
import ca.jbrains.pos.UnknownBarcodeSaleResult;

public class SellOneItemTest {

	private PointOfSale pointOfSale = mock(PointOfSale.class);

	private Display display = mock(Display.class);

	@Test
	public void productFound() throws Exception {
		when(pointOfSale.tryToSell("firstBarCode")).thenReturn(new SuccessfulSaleResult(12350));
		SaleTerminalListener saleTerminalListener = new SaleTerminalListener(display, null, pointOfSale);

		saleTerminalListener.onBarcode("firstBarCode");

		verify(display).displayPrice(12350);
	}

	@Test
	public void noProductFound() throws Exception {
		when(pointOfSale.tryToSell("unknown barCode")).thenReturn(new UnknownBarcodeSaleResult("unknown barCode"));
		SaleTerminalListener saleTerminalListener = new SaleTerminalListener(display, null, pointOfSale);

		saleTerminalListener.onBarcode("unknown barCode");

		verify(display).displayNoProductFound("unknown barCode");
	}

	@Test
	public void emptyBarcodeReceived() throws Exception {
		when(pointOfSale.tryToSell("")).thenReturn(new EmptyBarcodeSaleResult());
		SaleTerminalListener saleTerminalListener = new SaleTerminalListener(display, null, pointOfSale);
		saleTerminalListener.onBarcode("");

		verify(display).displayScannedEmptyBarcode();
	}

}

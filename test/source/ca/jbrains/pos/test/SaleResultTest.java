package ca.jbrains.pos.test;

import static org.mockito.Mockito.*;

import org.junit.Test;

import ca.jbrains.pos.Display;
import ca.jbrains.pos.EmptyBarcodeSaleResult;
import ca.jbrains.pos.SuccessfulSaleResult;
import ca.jbrains.pos.UnknownBarcodeSaleResult;

public class SaleResultTest {

	private Display display = mock(Display.class);

	@Test
	public void productFound() throws Exception {
		SuccessfulSaleResult saleResult = new SuccessfulSaleResult(12350);

		saleResult.renderOn(display);

		verify(display).displayPrice(12350);
	}

	@Test
	public void noProductFound() throws Exception {
		UnknownBarcodeSaleResult saleResult = new UnknownBarcodeSaleResult("unknown barCode");

		saleResult.renderOn(display);

		verify(display).displayNoProductFound("unknown barCode");
	}

	@Test
	public void emptyBarcodeReceived() throws Exception {
		EmptyBarcodeSaleResult saleResult = new EmptyBarcodeSaleResult();

		saleResult.renderOn(display);

		verify(display).displayScannedEmptyBarcode();
	}
}

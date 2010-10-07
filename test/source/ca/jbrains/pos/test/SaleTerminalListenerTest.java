package ca.jbrains.pos.test;

import static org.mockito.Mockito.*;

import org.junit.Test;

import ca.jbrains.pos.Display;
import ca.jbrains.pos.PointOfSale;
import ca.jbrains.pos.SaleResult;
import ca.jbrains.pos.SaleTerminalListener;

public class SaleTerminalListenerTest {

	private PointOfSale pointOfSale = mock(PointOfSale.class);

	private Display display = mock(Display.class);

	@Test
	public void renderResponse() {
		SaleResult saleResult = mock(SaleResult.class);
		when(pointOfSale.tryToSell("123")).thenReturn(saleResult);
		SaleTerminalListener saleTerminalListener = new SaleTerminalListener(display, pointOfSale);

		saleTerminalListener.onBarcode("123");

		verify(saleResult).renderOn(display);
	}

}

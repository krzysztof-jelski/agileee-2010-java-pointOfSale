package ca.jbrains.pos.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

import ca.jbrains.pos.Catalog;
import ca.jbrains.pos.EmptyBarcodeSaleResult;
import ca.jbrains.pos.PointOfSaleWithCatalog;
import ca.jbrains.pos.PointOfSale;
import ca.jbrains.pos.SaleResult;
import ca.jbrains.pos.SuccessfulSaleResult;
import ca.jbrains.pos.UnknownBarcodeSaleResult;

public class PointOfSaleTest {

	@Test
	public void successfulResultIfProductFoundInCatalog() {
		Catalog catalog = mock(Catalog.class);
		when(catalog.findCostFor("barcode")).thenReturn(12300);
		when(catalog.contains("barcode")).thenReturn(true);
		PointOfSale pointOfSale = new PointOfSaleWithCatalog(catalog);

		SaleResult saleResult = pointOfSale.tryToSell("barcode");

		assertEquals(SuccessfulSaleResult.class, saleResult.getClass());
		assertEquals(12300, ((SuccessfulSaleResult) saleResult).price);
	}

	@Test
	public void noProductFound() throws Exception {
		Catalog catalog = mock(Catalog.class);
		when(catalog.contains("unknown barCode")).thenReturn(false);
		PointOfSale pointOfSale = new PointOfSaleWithCatalog(catalog);

		SaleResult saleResult = pointOfSale.tryToSell("unknown barCode");

		assertEquals(UnknownBarcodeSaleResult.class, saleResult.getClass());
		assertEquals("unknown barCode", ((UnknownBarcodeSaleResult) saleResult).code);
	}

	@Test
	public void emptyBarcodeScanned() throws Exception {
		PointOfSale pointOfSale = new PointOfSaleWithCatalog(null);

		SaleResult saleResult = pointOfSale.tryToSell("");

		assertEquals(EmptyBarcodeSaleResult.class, saleResult.getClass());
	}
}

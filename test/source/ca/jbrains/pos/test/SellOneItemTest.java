package ca.jbrains.pos.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

import ca.jbrains.pos.Catalog;
import ca.jbrains.pos.CatalogItem;
import ca.jbrains.pos.EmptyBarcodeSaleResult;
import ca.jbrains.pos.PointOfSale;
import ca.jbrains.pos.PointOfSaleWithCatalog;
import ca.jbrains.pos.SaleResult;
import ca.jbrains.pos.SuccessfulSaleResult;
import ca.jbrains.pos.Taxes;
import ca.jbrains.pos.UnknownBarcodeSaleResult;

public class SellOneItemTest {

	private Taxes taxes = mock(Taxes.class);
	private Catalog catalog = mock(Catalog.class);

	private PointOfSale pointOfSale = new PointOfSaleWithCatalog(catalog, taxes);

	@Test
	public void noProductFound() throws Exception {
		when(catalog.contains("unknown barCode")).thenReturn(false);

		SaleResult saleResult = pointOfSale.tryToSell("unknown barCode");

		assertEquals(UnknownBarcodeSaleResult.class, saleResult.getClass());
		assertEquals("unknown barCode", ((UnknownBarcodeSaleResult) saleResult).code);
	}

	@Test
	public void emptyBarcodeScanned() throws Exception {
		SaleResult saleResult = pointOfSale.tryToSell("");

		assertEquals(EmptyBarcodeSaleResult.class, saleResult.getClass());
	}

	@Test
	public void calculateCostForItemFoundInCatalog() {
		CatalogItem item = new CatalogItem(0);
		when(catalog.contains("barcode")).thenReturn(true);
		when(catalog.findItem("barcode")).thenReturn(item);
		when(taxes.on(item)).thenReturn(10000);

		SaleResult saleResult = pointOfSale.tryToSell("barcode");

		assertEquals(SuccessfulSaleResult.class, saleResult.getClass());
		assertEquals(10000, ((SuccessfulSaleResult) saleResult).cost);
	}

}

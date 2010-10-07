package ca.jbrains.pos.test;

import static org.junit.Assert.*;

import org.junit.Test;

import ca.jbrains.pos.CatalogItem;
import ca.jbrains.pos.Taxes;

public class TaxesTest {
	@Test
	public void findCostWithGst() {
		Taxes taxes = Taxes.withGstAndPst(5, 10);
		CatalogItem catalogItem = new CatalogItemBuilder().withPrice(10000).pstExempt(true).build();

		int cost = taxes.on(catalogItem);

		assertEquals(10500, cost);
	}

	@Test
	public void findCostWithPst() {
		Taxes taxes = Taxes.withGstAndPst(5, 10);
		CatalogItem catalogItem = new CatalogItemBuilder().withPrice(10000).pstExempt(false).build();

		int cost = taxes.on(catalogItem);

		assertEquals(11550, cost);
	}
}

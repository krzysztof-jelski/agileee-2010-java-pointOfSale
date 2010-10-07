package ca.jbrains.pos.test;

import static org.junit.Assert.*;

import org.junit.Test;

import ca.jbrains.pos.Catalog;
import ca.jbrains.pos.Taxes;

public class CatalogTest {

	@Test
	public void findCostWithGst() {
		// given
		Catalog catalog = new Catalog(Taxes.withGstAndPst(5, 10));
		catalog.add("123", 100, true);

		// when
		int cost = catalog.findCostFor("123");

		// then
		assertEquals(105, cost);
	}

	@Test
	public void findCostWithPst() {
		// given
		Catalog catalog = new Catalog(Taxes.withGstAndPst(5, 10));
		catalog.add("123", 10000, false);

		// when
		int cost = catalog.findCostFor("123");

		// then
		assertEquals(11550, cost);

	}
}

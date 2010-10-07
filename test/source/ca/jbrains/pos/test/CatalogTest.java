package ca.jbrains.pos.test;

import static org.junit.Assert.*;

import org.junit.Test;

import ca.jbrains.pos.Catalog;
import ca.jbrains.pos.Taxes;

public class CatalogTest {

	@Test
	public void findCostWithGst() {
		Catalog catalog = new Catalog(Taxes.withGstAndPst(5, 10));
		catalog.add("123", 100, true);

		int cost = catalog.findCostFor("123");

		assertEquals(105, cost);
	}

	@Test
	public void findCostWithPst() {
		Catalog catalog = new Catalog(Taxes.withGstAndPst(5, 10));
		catalog.add("123", 10000, false);

		int cost = catalog.findCostFor("123");

		assertEquals(11550, cost);
	}
}

package ca.jbrains.pos.test;

import static org.junit.Assert.*;

import org.junit.Test;

import ca.jbrains.pos.Catalog;
import ca.jbrains.pos.Taxes;

public class CatalogTest {

	@Test
	public void findCostWithTaxes() {
		// given
		Catalog catalog = new Catalog(new Taxes().withGst(5).withPst(10));
		catalog.add("123", 100, true);

		// when
		int cost = catalog.findCostFor("123");

		// then
		assertEquals(105, cost);
	}
}

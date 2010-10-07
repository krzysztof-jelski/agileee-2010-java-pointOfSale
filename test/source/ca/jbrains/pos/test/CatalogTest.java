package ca.jbrains.pos.test;

import static org.junit.Assert.*;

import java.util.Collections;

import org.junit.Test;

import ca.jbrains.pos.Catalog;
import ca.jbrains.pos.CatalogItem;

public class CatalogTest {

	@Test
	public void itemFound() {
		CatalogItem catalogItem = new CatalogItem(100);
		Catalog catalog = Catalog.with("123", catalogItem);

		CatalogItem foundCatalogItem = catalog.findItem("123");

		assertEquals(catalogItem, foundCatalogItem);
	}

	@Test
	public void itemNotFound() {
		Catalog catalog = new Catalog(Collections.<String, CatalogItem> emptyMap());

		assertFalse("How could you find something in the empty catalog?!", catalog.contains("123"));
	}

	@Test
	public void containItem() {
		Catalog catalog = Catalog.with("123", 0);

		assertTrue("Item should be in catalog", catalog.contains("123"));
	}
}

package ca.jbrains.pos.test;

import static org.junit.Assert.*;

import java.util.Collections;

import org.junit.Test;

import ca.jbrains.pos.Catalog;
import ca.jbrains.pos.CatalogItem;

public class CatalogTest {

	@Test
	public void itemFound() {
		CatalogItem catalogItem = new CatalogItemBuilder().withCode("123").build();
		Catalog catalog = Catalog.with(catalogItem);

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
		Catalog catalog = Catalog.with(new CatalogItemBuilder().withCode("123").build());

		assertTrue("Item should be in catalog", catalog.contains("123"));
	}

	@Test
	public void mapCatalogItemByCode() {
		CatalogItem catalogItem = new CatalogItem("code", 123, true);
		Catalog catalog = Catalog.with(catalogItem);

		CatalogItem foundItem = catalog.findItem("code");

		assertEquals(catalogItem, foundItem);
	}
}

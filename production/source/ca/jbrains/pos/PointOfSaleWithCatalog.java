package ca.jbrains.pos;

public class PointOfSaleWithCatalog implements PointOfSale {

	private final Catalog catalog;
	private final Taxes taxes;

	public PointOfSaleWithCatalog(Catalog catalog, Taxes taxes) {
		this.catalog = catalog;
		this.taxes = taxes;
	}

	@Override
	public SaleResult tryToSell(String code) {
		if (code.isEmpty()) {
			return new EmptyBarcodeSaleResult();
		}
		if (catalog.contains(code)) {
			return new SuccessfulSaleResult(taxes.on(catalog.findItem(code)));
		}
		return new UnknownBarcodeSaleResult(code);
	}

}

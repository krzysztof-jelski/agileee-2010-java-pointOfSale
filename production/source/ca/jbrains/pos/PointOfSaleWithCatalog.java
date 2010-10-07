package ca.jbrains.pos;

public class PointOfSaleWithCatalog implements PointOfSale {

	private final Catalog catalog;

	public PointOfSaleWithCatalog(Catalog catalog) {
		this.catalog = catalog;
	}

	@Override
	public SaleResult tryToSell(String code) {
		if (code.isEmpty()) {
			return new EmptyBarcodeSaleResult();
		}
		if (catalog.contains(code)) {
			return new SuccessfulSaleResult(catalog.findCostFor(code));
		}
		return new UnknownBarcodeSaleResult(code);
	}

}

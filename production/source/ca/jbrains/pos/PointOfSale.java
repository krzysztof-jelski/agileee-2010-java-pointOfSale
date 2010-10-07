package ca.jbrains.pos;

public interface PointOfSale {

	SaleResult tryToSell(String code);

}

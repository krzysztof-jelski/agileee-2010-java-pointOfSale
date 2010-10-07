package ca.jbrains.pos;

public class Main {

	public static void main(String[] args) {
		Catalog catalog = new Catalog();
		catalog.add("123", "$256.50");
		PointOfSale pointOfSale = new PointOfSale(new Screen(), catalog);
		pointOfSale.onBarcode("123");
		pointOfSale.onBarcode("000");
		pointOfSale.onBarcode("");
	}

}

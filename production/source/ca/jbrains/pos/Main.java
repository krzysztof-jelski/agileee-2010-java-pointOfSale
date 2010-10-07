package ca.jbrains.pos;

public class Main {

	public static void main(String[] args) {
		Catalog catalog = Catalog.with("123", 25650);
		PointOfSale pointOfSale = new PointOfSale(new Screen(System.out), catalog);
		pointOfSale.onBarcode("123");
		pointOfSale.onBarcode("000");
		pointOfSale.onBarcode("");
	}

}

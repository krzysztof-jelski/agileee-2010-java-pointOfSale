package ca.jbrains.pos;

public class Main {

	public static void main(String[] args) {
		Catalog catalog = Catalog.with("123", 25650);
		SaleTerminalListener saleTerminalListener = new SaleTerminalListener(new Screen(System.out), catalog);
		saleTerminalListener.onBarcode("123");
		saleTerminalListener.onBarcode("000");
		saleTerminalListener.onBarcode("");
	}

}

package ca.jbrains.pos;

public class SuccessfulSaleResult implements SaleResult {

	public final int price;

	public SuccessfulSaleResult(int price) {
		this.price = price;
	}

	@Override
	public void renderOn(Display display) {
		display.displayPrice(price);
	}

}

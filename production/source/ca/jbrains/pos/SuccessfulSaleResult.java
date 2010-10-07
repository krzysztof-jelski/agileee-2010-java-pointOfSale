package ca.jbrains.pos;

public class SuccessfulSaleResult implements SaleResult {

	public final int cost;

	public SuccessfulSaleResult(int cost) {
		this.cost = cost;
	}

	@Override
	public void renderOn(Display display) {
		display.displayPrice(cost);
	}

}

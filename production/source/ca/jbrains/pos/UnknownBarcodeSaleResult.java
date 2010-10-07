package ca.jbrains.pos;

public class UnknownBarcodeSaleResult implements SaleResult {

	private final String code;

	public UnknownBarcodeSaleResult(String code) {
		this.code = code;
	}

	@Override
	public void renderOn(Display display) {
		display.displayNoProductFound(code);
	}

}

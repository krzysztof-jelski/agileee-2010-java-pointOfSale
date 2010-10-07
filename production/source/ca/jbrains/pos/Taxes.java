package ca.jbrains.pos;

public class Taxes {

	public final int gst;
	public final int pst;

	private Taxes(int gst, int pst) {
		this.gst = gst;
		this.pst = pst;
	}

	public static Taxes withGstAndPst(int gst, int pst) {
		return new Taxes(gst, pst);
	}

	int gstTax(int price) {
		return percent(price, gst);
	}

	public int pstTax(int price) {
		return percent(price, pst);
	}

	private int percent(int price, int percent) {
		return price / 100 * percent;
	}

}

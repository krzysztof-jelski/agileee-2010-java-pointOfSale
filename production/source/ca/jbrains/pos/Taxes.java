package ca.jbrains.pos;

public class Taxes {

	public int gst;
	private int pst;

	public Taxes withGst(int gst) {
		this.gst = gst;
		return this;
	}

	public Taxes withPst(int pst) {
		this.pst = pst;
		return this;
	}

}

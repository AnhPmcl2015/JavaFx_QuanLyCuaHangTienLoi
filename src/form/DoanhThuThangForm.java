package form;

import javafx.beans.property.SimpleStringProperty;

public class DoanhThuThangForm {
	private SimpleStringProperty ngay, in, out, rev;

	public DoanhThuThangForm() {
		this(null, null, null, null);
	}

	public DoanhThuThangForm(String ngay, String in, String out, String rev) {
		super();
		this.ngay = new SimpleStringProperty(ngay);
		this.in = new SimpleStringProperty(in);
		this.out = new SimpleStringProperty(out);
		this.rev = new SimpleStringProperty(rev);
	}

	public SimpleStringProperty getNgay() {
		return ngay;
	}

	public SimpleStringProperty getIn() {
		return in;
	}

	public SimpleStringProperty getOut() {
		return out;
	}

	public SimpleStringProperty getRev() {
		return rev;
	}

	public void setNgay(SimpleStringProperty ngay) {
		this.ngay = ngay;
	}

	public void setIn(SimpleStringProperty in) {
		this.in = in;
	}

	public void setOut(SimpleStringProperty out) {
		this.out = out;
	}

	public void setRev(SimpleStringProperty rev) {
		this.rev = rev;
	}

}

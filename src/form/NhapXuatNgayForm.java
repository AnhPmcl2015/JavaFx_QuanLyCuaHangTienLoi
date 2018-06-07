package form;

import javafx.beans.property.SimpleStringProperty;

public class NhapXuatNgayForm {
	private SimpleStringProperty ma, soLuong;

	public NhapXuatNgayForm() {
		this(null,null);
	}
	
	public NhapXuatNgayForm(String ma, String soLuong) {
		super();
		this.ma = new SimpleStringProperty(ma);
		this.soLuong = new SimpleStringProperty(soLuong);
	}

	public SimpleStringProperty getMa() {
		return ma;
	}

	public SimpleStringProperty getSoLuong() {
		return soLuong;
	}

	public void setMa(SimpleStringProperty ma) {
		this.ma = ma;
	}

	public void setSoLuong(SimpleStringProperty soLuong) {
		this.soLuong = soLuong;
	}
	
}

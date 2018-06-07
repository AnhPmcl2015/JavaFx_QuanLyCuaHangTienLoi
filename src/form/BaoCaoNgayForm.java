package form;

import javafx.beans.property.SimpleStringProperty;

public class BaoCaoNgayForm {
	private SimpleStringProperty ma, tien;

	public BaoCaoNgayForm() {
		this(null,null);
	}
	
	public BaoCaoNgayForm(String ma, String tien) {
		super();
		this.ma = new SimpleStringProperty(ma);
		this.tien = new SimpleStringProperty(tien);
	}

	public SimpleStringProperty getMa() {
		return ma;
	}

	public SimpleStringProperty getTien() {
		return tien;
	}

	public void setMa(SimpleStringProperty ma) {
		this.ma = ma;
	}

	public void setTien(SimpleStringProperty tien) {
		this.tien = tien;
	}
	
}

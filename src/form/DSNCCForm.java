package form;

import javafx.beans.property.SimpleStringProperty;

public class DSNCCForm {
	private SimpleStringProperty maNCC, tenNCC, diaChi, sdt, email;

	public DSNCCForm() {
		this(null, null, null, null, null);
	}

	public DSNCCForm(String maNCC, String tenNCC, String diaChi, String sdt, String email) {
		super();
		this.maNCC = new SimpleStringProperty(maNCC);
		this.tenNCC = new SimpleStringProperty(tenNCC);
		this.diaChi = new SimpleStringProperty(diaChi);
		this.sdt = new SimpleStringProperty(sdt);
		this.email = new SimpleStringProperty(email);
	}

	public SimpleStringProperty getMaNCC() {
		return maNCC;
	}

	public SimpleStringProperty getTenNCC() {
		return tenNCC;
	}

	public SimpleStringProperty getDiaChi() {
		return diaChi;
	}

	public SimpleStringProperty getSdt() {
		return sdt;
	}

	public SimpleStringProperty getEmail() {
		return email;
	}

	public void setMaNCC(SimpleStringProperty maNCC) {
		this.maNCC = maNCC;
	}

	public void setTenNCC(SimpleStringProperty tenNCC) {
		this.tenNCC = tenNCC;
	}

	public void setDiaChi(SimpleStringProperty diaChi) {
		this.diaChi = diaChi;
	}

	public void setSdt(SimpleStringProperty sdt) {
		this.sdt = sdt;
	}

	public void setEmail(SimpleStringProperty email) {
		this.email = email;
	}

}

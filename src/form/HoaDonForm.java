package form;

import javafx.beans.property.SimpleStringProperty;

public class HoaDonForm {
	private SimpleStringProperty maSP, tenSP, soLuong, donGia, donViTinh, thanhTien;

	public HoaDonForm(String maSP, String tenSP, String soLuong, String donGia, String donViTinh, String thanhTien) {
		super();
		this.maSP = new SimpleStringProperty(maSP);
		this.tenSP = new SimpleStringProperty(tenSP);
		this.soLuong = new SimpleStringProperty(soLuong);
		this.donGia = new SimpleStringProperty(donGia);
		this.donViTinh = new SimpleStringProperty(donViTinh);
		this.thanhTien = new SimpleStringProperty(thanhTien);
	}

	public HoaDonForm() {
		this(null, null, null, null, null, null);
	}

	public SimpleStringProperty getMaSP() {
		return maSP;
	}

	public SimpleStringProperty getTenSP() {
		return tenSP;
	}

	public SimpleStringProperty getSoLuong() {
		return soLuong;
	}

	public SimpleStringProperty getDonGia() {
		return donGia;
	}

	public SimpleStringProperty getDonViTinh() {
		return donViTinh;
	}

	public SimpleStringProperty getThanhTien() {
		return thanhTien;
	}

	public void setMaSP(SimpleStringProperty maSP) {
		this.maSP = maSP;
	}

	public void setTenSP(SimpleStringProperty tenSP) {
		this.tenSP = tenSP;
	}

	public void setSoLuong(SimpleStringProperty soLuong) {
		this.soLuong = soLuong;
	}

	public void setDonGia(SimpleStringProperty donGia) {
		this.donGia = donGia;
	}

	public void setDonViTinh(SimpleStringProperty donViTinh) {
		this.donViTinh = donViTinh;
	}

	public void setThanhTien(SimpleStringProperty thanhTien) {
		this.thanhTien = thanhTien;
	}

}

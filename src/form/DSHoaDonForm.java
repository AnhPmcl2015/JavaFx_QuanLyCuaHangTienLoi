package form;

import javafx.beans.property.SimpleStringProperty;

public class DSHoaDonForm {
	private SimpleStringProperty maHoaDon, tenNhanVien, ngayLap, tongCong;

	public DSHoaDonForm() {
		this(null,null,null,null);
	}
	
	
	public DSHoaDonForm(String maHoaDon, String tenNhanVien, String ngayLap,
			String tongCong) {
		super();
		this.maHoaDon = new SimpleStringProperty(maHoaDon);
		this.tenNhanVien = new SimpleStringProperty(tenNhanVien);
		this.ngayLap = new SimpleStringProperty(ngayLap);
		this.tongCong = new SimpleStringProperty(tongCong);
	}

	public SimpleStringProperty getMaHoaDon() {
		return maHoaDon;
	}

	public SimpleStringProperty getTenNhanVien() {
		return tenNhanVien;
	}

	public SimpleStringProperty getNgayLap() {
		return ngayLap;
	}

	public SimpleStringProperty getTongCong() {
		return tongCong;
	}

	public void setMaHoaDon(SimpleStringProperty maHoaDon) {
		this.maHoaDon = maHoaDon;
	}

	public void setTenNhanVien(SimpleStringProperty tenNhanVien) {
		this.tenNhanVien = tenNhanVien;
	}

	public void setNgayLap(SimpleStringProperty ngayLap) {
		this.ngayLap = ngayLap;
	}

	public void setTongCong(SimpleStringProperty tongCong) {
		this.tongCong = tongCong;
	}
	
	
}

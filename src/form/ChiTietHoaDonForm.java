package form;

import javafx.beans.property.SimpleStringProperty;

public class ChiTietHoaDonForm {
	private SimpleStringProperty barCode, tenSp, dvt, gia, soLuong, thanhTien;

	public ChiTietHoaDonForm() {
		this(null, null, null, null, null, null);
	}

	public ChiTietHoaDonForm(String barCode, String tenSp, String dvt, String gia, String soLuong, String thanhTien) {
		super();
		this.barCode = new SimpleStringProperty(barCode);
		this.tenSp = new SimpleStringProperty(tenSp);
		this.dvt = new SimpleStringProperty(dvt);
		this.gia = new SimpleStringProperty(gia);
		this.soLuong = new SimpleStringProperty(soLuong);
		this.thanhTien = new SimpleStringProperty(thanhTien);
	}

	public SimpleStringProperty getBarCode() {
		return barCode;
	}

	public SimpleStringProperty getTenSp() {
		return tenSp;
	}

	public SimpleStringProperty getDvt() {
		return dvt;
	}

	public SimpleStringProperty getGia() {
		return gia;
	}

	public SimpleStringProperty getSoLuong() {
		return soLuong;
	}

	public SimpleStringProperty getThanhTien() {
		return thanhTien;
	}

	public void setBarCode(SimpleStringProperty barCode) {
		this.barCode = barCode;
	}

	public void setTenSp(SimpleStringProperty tenSp) {
		this.tenSp = tenSp;
	}

	public void setDvt(SimpleStringProperty dvt) {
		this.dvt = dvt;
	}

	public void setGia(SimpleStringProperty gia) {
		this.gia = gia;
	}

	public void setSoLuong(SimpleStringProperty soLuong) {
		this.soLuong = soLuong;
	}

	public void setThanhTien(SimpleStringProperty thanhTien) {
		this.thanhTien = thanhTien;
	}

}

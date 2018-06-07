package form;

import javafx.beans.property.SimpleStringProperty;

public class ThemNhanVienForm {
	private SimpleStringProperty maNv, tenNv, gioiTinh, ngaySinh, chucVu;

	public ThemNhanVienForm() {
		this(null,null,null,null,null);
	}
	
	public ThemNhanVienForm(String maNv, String tenNv, String gioiTinh,
			String ngaySinh, String chucVu) {
		super();
		this.maNv = new SimpleStringProperty(maNv);
		this.tenNv = new SimpleStringProperty(tenNv);
		this.gioiTinh = new SimpleStringProperty(gioiTinh);
		this.ngaySinh = new SimpleStringProperty(ngaySinh);
		this.chucVu = new SimpleStringProperty(chucVu);
	}

	public SimpleStringProperty getMaNv() {
		return maNv;
	}

	public SimpleStringProperty getTenNv() {
		return tenNv;
	}

	public SimpleStringProperty getGioiTinh() {
		return gioiTinh;
	}

	public SimpleStringProperty getNgaySinh() {
		return ngaySinh;
	}

	public SimpleStringProperty getChucVu() {
		return chucVu;
	}

	public void setMaNv(SimpleStringProperty maNv) {
		this.maNv = maNv;
	}

	public void setTenNv(SimpleStringProperty tenNv) {
		this.tenNv = tenNv;
	}

	public void setGioiTinh(SimpleStringProperty gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public void setNgaySinh(SimpleStringProperty ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public void setChucVu(SimpleStringProperty chucVu) {
		this.chucVu = chucVu;
	}
	
	
}

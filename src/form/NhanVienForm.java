package form;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.ImageView;

public class NhanVienForm {
	private ImageView hinhAnh;
	private SimpleStringProperty maNv, tenMv, ngaySinh, gioiTinh;

	public NhanVienForm(ImageView hinhAnh, String maNv, String tenMv, String ngaySinh, String gioiTinh) {
		super();
		this.hinhAnh = hinhAnh;
		this.maNv = new SimpleStringProperty(maNv);
		this.tenMv = new SimpleStringProperty(tenMv);
		this.ngaySinh = new SimpleStringProperty(ngaySinh);
		this.gioiTinh = new SimpleStringProperty(gioiTinh);
	}

	public NhanVienForm() {
		this(new ImageView(), null, null, null, null);
	}

	public ImageView getHinhAnh() {
		return hinhAnh;
	}


	public SimpleStringProperty getMaNv() {
		return maNv;
	}

	public SimpleStringProperty getTenMv() {
		return tenMv;
	}

	public SimpleStringProperty getNgaySinh() {
		return ngaySinh;
	}

	public SimpleStringProperty getGioiTinh() {
		return gioiTinh;
	}


	public void setHinhAnh(ImageView hinhAnh) {
		this.hinhAnh = hinhAnh;
	}

	public void setMaNv(SimpleStringProperty maNv) {
		this.maNv = maNv;
	}

	public void setTenMv(SimpleStringProperty tenMv) {
		this.tenMv = tenMv;
	}

	public void setNgaySinh(SimpleStringProperty ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public void setGioiTinh(SimpleStringProperty gioiTinh) {
		this.gioiTinh = gioiTinh;
	}


}

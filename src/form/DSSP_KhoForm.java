package form;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.ImageView;

public class DSSP_KhoForm {
	private SimpleStringProperty maSp, tenSp, soLuongKho, soLuongBan;
	private ImageView image;

	public DSSP_KhoForm(String maSp, String tenSp, String soLuongKho, String soLuongBan, ImageView image) {
		super();
		this.maSp = new SimpleStringProperty(maSp);
		this.tenSp = new SimpleStringProperty(tenSp);
		this.soLuongKho = new SimpleStringProperty(soLuongKho);
		this.soLuongBan = new SimpleStringProperty(soLuongBan);
		this.image = image;
	}

	public DSSP_KhoForm() {
		this(null, null, null, null, new ImageView());
	}

	public SimpleStringProperty getMaSp() {
		return maSp;
	}

	public SimpleStringProperty getTenSp() {
		return tenSp;
	}

	public SimpleStringProperty getSoLuongKho() {
		return soLuongKho;
	}

	public SimpleStringProperty getSoLuongBan() {
		return soLuongBan;
	}

	public ImageView getImage() {
		return image;
	}

	public void setMaSp(SimpleStringProperty maSp) {
		this.maSp = maSp;
	}

	public void setTenSp(SimpleStringProperty tenSp) {
		this.tenSp = tenSp;
	}

	public void setSoLuongKho(SimpleStringProperty soLuongKho) {
		this.soLuongKho = soLuongKho;
	}

	public void setSoLuongBan(SimpleStringProperty soLuongBan) {
		this.soLuongBan = soLuongBan;
	}

	public void setImage(ImageView image) {
		this.image = image;
	}

}

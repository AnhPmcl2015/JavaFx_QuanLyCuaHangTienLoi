package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.json.JSONObject;

import contants.Contants;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class ChinhSuaThongTinNhanVienController implements Initializable {

	@FXML
	private ImageView imgHinhAnh;

	@FXML
	private TextField txtMaNV, txtTenNV, txtCMND, txtNgaySinh, txtSDT, txtGioiTinh;

	@FXML
	private Button btnThoat;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			addControls();
		} catch (IOException e) {
			e.printStackTrace();
		}
		addEvents();
	}

	private void addControls() throws IOException {
		imgHinhAnh.setImage(Contants.nhanVienDuocChon.getHinhAnh().getImage());
		String url = "https://convenient-store.azurewebsites.net/api/staffs/"
				+ Contants.nhanVienDuocChon.getMaNv().get() + "?accountId=1";
		
		String json = Contants.getJSon(url);
		JSONObject obj = new JSONObject(json);
		String cmnd = obj.getLong("identityNumber") + "";
		String maNv = Contants.nhanVienDuocChon.getMaNv().get();
		String sdt = obj.getLong("phoneNumber") + "";
		String tenNv = obj.getString("fullName");
		String gioiTinh = obj.getString("gender");
		String ngaySinh = Contants.nhanVienDuocChon.getNgaySinh().get();
		
		txtMaNV.setText(maNv);
		txtTenNV.setText(tenNv);
		txtCMND.setText(cmnd);
		txtNgaySinh.setText(cmnd);
		txtGioiTinh.setText(gioiTinh);
		txtSDT.setText(sdt);
		txtNgaySinh.setText(ngaySinh);
	}

	private void addEvents() {
		btnThoat.setOnMouseClicked(e->{
			Stage stage = (Stage) btnThoat.getScene().getWindow();
			stage.close();
		});
	}

}

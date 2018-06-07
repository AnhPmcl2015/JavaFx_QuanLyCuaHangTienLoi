package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import org.json.JSONArray;
import org.json.JSONObject;

import contants.Contants;
import form.ThemNhanVienForm;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ThemNhanVienController implements Initializable {
	private static ObservableList<ThemNhanVienForm> dsNhanVien;
	private Image imgDuocChon;
	private String imageName = "";

	@FXML
	private ImageView imgHinhAnh, imgChonHinhAnh;

	@FXML
	private TextField txtTenNhanVien, txtCMND, txtSDT, txtTaiKhoan, txtMatKhau, txtNgay, txtThang, txtNam;

	@FXML
	private DatePicker dtpNgaySinh;

	@FXML
	private ComboBox<String> cmbGioiTinh, cmbChucVu;

	@FXML
	private Button btnXoaThongTin, btnDongY;

	@FXML
	private TableView<ThemNhanVienForm> tvThemNhanVien;

	@FXML
	private TableColumn<ThemNhanVienForm, String> tcMaNV, tcTenNV, tcGioiTinh, tcNgaySinh, tcChucVu;

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
		ObservableList<String> gioiTinh = FXCollections.observableArrayList("Nam", "Nữ");
		cmbGioiTinh.getItems().addAll(gioiTinh);
		cmbGioiTinh.getSelectionModel().select(0);

		ObservableList<String> chucVu = FXCollections.observableArrayList("Thu ngân", "Kho", "Quản lý");
		cmbChucVu.getItems().addAll(chucVu);
		cmbChucVu.getSelectionModel().select(0);

		dtpNgaySinh.setValue(null);

		if (dsNhanVien == null) {
			dsNhanVien = FXCollections.observableArrayList();
		} else {
			dsNhanVien.clear();
		}
		
		tcMaNV.setCellValueFactory(cellData -> cellData.getValue().getMaNv());
		tcTenNV.setCellValueFactory(cellData -> cellData.getValue().getTenNv());
		tcNgaySinh.setCellValueFactory(cellData -> cellData.getValue().getNgaySinh());
		tcGioiTinh.setCellValueFactory(cellData -> cellData.getValue().getGioiTinh());
		tcChucVu.setCellValueFactory(cellData -> cellData.getValue().getChucVu());
		tvThemNhanVien.setItems(dsNhanVien);
		
		fillTable();
	}
	
	private void fillTable() throws IOException {
		String url = "https://convenient-store.azurewebsites.net/api/staffs?accountId=1";
		String json = Contants.getJSon(url);
		JSONArray array = new JSONArray(json);
		for(int i = 0; i < array.length();i++) {
			JSONObject obj = array.getJSONObject(i);
			String maNv = obj.getInt("staffId")+"";
			String tenNv = obj.getString("fullName");
			String ngaySinh = obj.getString("dateOfBirth");
			ngaySinh = ngaySinh.substring(0, ngaySinh.indexOf("T"));
			String gt = obj.getString("gender");
			String cv = obj.getString("role");
			dsNhanVien.add(new ThemNhanVienForm(maNv, tenNv, gt, ngaySinh, cv));
		}
	}

	private void addEvents() {
		btnDongY.setOnMouseClicked(e -> {
			try {
				themNhanVien();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});

		btnXoaThongTin.setOnMouseClicked(e -> {
			xoaDuLieuDaNhap();
		});

		imgChonHinhAnh.setOnMouseClicked(e -> {
			chonHinhAnh();
		});

		dtpNgaySinh.valueProperty().addListener(new ChangeListener<LocalDate>() {
			@Override
			public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue,
					LocalDate newValue) {
				if (newValue != null) {
					capNhatNgaySinh(newValue);
				}
			}
		});
	}

	// Xoa du lieu da nhap
	private void xoaDuLieuDaNhap() {
		imgHinhAnh.setImage(new Image("/resources/noImage.jpg"));
		txtTenNhanVien.setText("");
		txtCMND.setText("");
		txtSDT.setText("");
		txtNgay.setText("");
		txtThang.setText("");
		txtNam.setText("");
		cmbGioiTinh.getSelectionModel().select(0);
		cmbChucVu.getSelectionModel().select(0);
		txtTaiKhoan.setText("");
		txtMatKhau.setText("");
		txtTenNhanVien.requestFocus();
		dtpNgaySinh.setValue(null);
		imageName = "";
	}

	// Chon hinh anh de them vao hinh anh nhan vien
	private void chonHinhAnh() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Chọn hình ảnh");

		Stage stage = (Stage) btnDongY.getScene().getWindow();

		fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Files", "*.*"),
				new FileChooser.ExtensionFilter("JPG", "*.jpg"), new FileChooser.ExtensionFilter("PNG", "*.png"));
		File file = fileChooser.showOpenDialog(stage);
		String uri = file.toURI().toString();
		imageName = uri.substring(uri.lastIndexOf("/") + 1, uri.lastIndexOf("."));
		imgDuocChon = new Image(file.toURI().toString());
		imgHinhAnh.setImage(imgDuocChon);

	}

	// them nhan vien
	private void themNhanVien() throws IOException {
		if ("".equals(imageName))
			return;
		String imageUrl = "nhanVien/" + imageName + ".png";

		BufferedImage bimage = SwingFXUtils.fromFXImage(imgDuocChon, null);
		File save = new File(Contants.USER_PATH + imageUrl);
		try {
			ImageIO.write(bimage, "png", save);

		} catch (IOException e) {
		}

		String fullName = txtTenNhanVien.getText().trim();
		String firstName = fullName.substring(0, fullName.lastIndexOf(" "));
		String lastName = fullName.substring(fullName.lastIndexOf(" ") + 1);
		String dateOfBirth = txtNam + "-" + txtThang + "-" + txtNgay;
		String identityNumber = txtCMND.getText();
		String phoneNumber = txtSDT.getText();
		String gender = cmbGioiTinh.getSelectionModel().getSelectedIndex()+"";
		JSONArray array = new JSONArray();
		JSONObject obj1 = new JSONObject();
		obj1.put("firstName", firstName);
		obj1.put("lastName", lastName);
		obj1.put("dateOfBirth", dateOfBirth);
		obj1.put("identityNumber", identityNumber);
		obj1.put("gender", gender);
		obj1.put("phoneNumber", phoneNumber);
		obj1.put("imageUrl", imageUrl);
		
		String userName = txtTaiKhoan.getText();
		String password = txtMatKhau.getText();
		String roleId = "2";
		if(cmbChucVu.getSelectionModel().getSelectedIndex() == 0) {
			roleId = "3";
		}else if(cmbChucVu.getSelectionModel().getSelectedIndex() == 1) {
			roleId = "4";
		}
		
		JSONObject obj2 = new JSONObject();
		obj2.put("username", userName);
		obj2.put("password", password);
		obj2.put("roleId", roleId);
		
		array.put(obj1);
		array.put(obj2);
		System.out.println(array.toString());
		
		try {
			Contants.postJson("https://convenient-store.azurewebsites.net/api/staffs?accountId=1", array.toString());
			Alert dialog = new Alert(AlertType.INFORMATION);
			dialog.setTitle("Thông báo");
			dialog.setHeaderText("Thêm nhân viên thành công");
			dialog.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		dsNhanVien.clear();
		fillTable();

		xoaDuLieuDaNhap();
	}

	// Cap nhat ngay sinh cua nhan vien
	private void capNhatNgaySinh(LocalDate newValue) {
		String ngay = newValue.getDayOfMonth() + "";
		String thang = newValue.getMonthValue() + "";
		String nam = newValue.getYear() + "";

		dtpNgaySinh.setValue(null);
		txtNgay.setText(ngay);
		txtThang.setText(thang);
		txtNam.setText(nam);
	}
}

package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import org.json.JSONArray;
import org.json.JSONObject;

import contants.Contants;
import form.NhanVienForm;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

public class DSNhanVienController implements Initializable {

	private static ObservableList<NhanVienForm> dsNhanVien;
	private static ObservableList<NhanVienForm> dsNhanVien_Temp;
	private TableColumn<NhanVienForm, Void> tcXoa;

	@FXML
	private TextField txtMaNhanVien;

	@FXML
	private TableView<NhanVienForm> tbDSNhanVien;

	@FXML
	private TableColumn<NhanVienForm, Image> tcHinhAnh;

	@FXML
	private TableColumn<NhanVienForm, String> tcMaNV, tcTenNV, tcNgaySinh, tcGioiTinh;

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
		if (dsNhanVien == null) {
			dsNhanVien = FXCollections.observableArrayList();
		} else {
			dsNhanVien.clear();
		}

		if (dsNhanVien_Temp == null) {
			dsNhanVien_Temp = FXCollections.observableArrayList();
		}else {
			dsNhanVien_Temp.clear();
		}

		tcHinhAnh.setCellValueFactory(new PropertyValueFactory<>("hinhAnh"));
		tcMaNV.setCellValueFactory(cellData -> cellData.getValue().getMaNv());
		tcTenNV.setCellValueFactory(cellData -> cellData.getValue().getTenMv());
		tcNgaySinh.setCellValueFactory(cellData -> cellData.getValue().getNgaySinh());
		tcGioiTinh.setCellValueFactory(cellData -> cellData.getValue().getGioiTinh());

		tcXoa = new TableColumn<>("Xóa");
		Callback<TableColumn<NhanVienForm, Void>, TableCell<NhanVienForm, Void>> cellFactory = new Callback<TableColumn<NhanVienForm, Void>, TableCell<NhanVienForm, Void>>() {

			@Override
			public TableCell<NhanVienForm, Void> call(TableColumn<NhanVienForm, Void> param) {
				final TableCell<NhanVienForm, Void> cell = new TableCell<NhanVienForm, Void>() {
					private final Button btn = new Button("X");
					{
						btn.setOnAction(event -> {
							Alert dialog = new Alert(AlertType.CONFIRMATION);
							dialog.setTitle("Xóa");
							dialog.setHeaderText("Bạn muốn xóa nhân viên này?");
							Optional<ButtonType> option = dialog.showAndWait();

							if (option.get() == ButtonType.OK) {
								int pos = getIndex();
								try {
									xoaNhanVien(pos);
								} catch (IOException e) {
									e.printStackTrace();
								}
							} else if (option.get() == ButtonType.CANCEL) {
								dialog.close();
								event.consume();
							}
						});
					}

					protected void updateItem(Void item, boolean empty) {
						super.updateItem(item, empty);
						if (empty) {
							setGraphic(null);
						} else {
							setGraphic(btn);
						}
					};
				};
				return cell;
			}
		};

		tcXoa.setCellFactory(cellFactory);
		tcXoa.setMaxWidth(80);
		tbDSNhanVien.getColumns().add(tcXoa);

		tbDSNhanVien.setItems(dsNhanVien_Temp);

		String url = "https://convenient-store.azurewebsites.net/api/staffs?accountId=1";
		String json = Contants.getJSon(url);

		JSONArray array = new JSONArray(json);
		for (int i = 0; i < array.length(); i++) {
			JSONObject obj = array.getJSONObject(i);
			String maNv = obj.getInt("staffId") + "";
			String tenNv = obj.getString("fullName");
			String ngaySinh = obj.getString("dateOfBirth");
			ngaySinh = ngaySinh.substring(0, ngaySinh.indexOf("T"));
			String gender = obj.getString("gender");
			String imageUrl = "https://dantricdn.com/thumb_w/640/2017/ducphuc-1501377099478.jpg";
			ImageView hinhAnh = new ImageView(new Image(imageUrl));
			hinhAnh.setFitWidth(100);
			hinhAnh.setFitHeight(100);
			dsNhanVien.add(new NhanVienForm(hinhAnh, maNv, tenNv, ngaySinh, gender));
		}

		for (NhanVienForm nv : dsNhanVien) {
			dsNhanVien_Temp.add(nv);
		}
	}

	private void addEvents() {
		// Double click vao row se hien thong tin nhan vien
		tbDSNhanVien.setRowFactory(tv -> {
			TableRow<NhanVienForm> row = new TableRow<>();
			row.setOnMouseClicked(e -> {
				if (e.getClickCount() == 2 && !(row.isEmpty())) {
					try {
						hienThiThongTinNhanVien(row.getItem());
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			});

			return row;
		});

		txtMaNhanVien.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				timKiemNhanVien(newValue);
			}
		});
	}

	// Hien thi cua so hien thi thong tin nhan vien
	private void hienThiThongTinNhanVien(NhanVienForm nhanVien) throws IOException {

		if (Contants.nhanVienDuocChon == null) {
			Contants.nhanVienDuocChon = new NhanVienForm();
		}

		Contants.nhanVienDuocChon.setHinhAnh(nhanVien.getHinhAnh());
		Contants.nhanVienDuocChon.setMaNv(nhanVien.getMaNv());
		Contants.nhanVienDuocChon.setNgaySinh(nhanVien.getNgaySinh());

		Parent root = FXMLLoader.load(getClass().getResource("/gui/ChinhSuaThongTinNhanVien.fxml"));
		Scene scene = new Scene(root, 350, 620);
		Stage thongTinNhanVien = new Stage();
		thongTinNhanVien.setScene(scene);
		thongTinNhanVien.initModality(Modality.WINDOW_MODAL);
		thongTinNhanVien.setResizable(false);
		thongTinNhanVien.setTitle("Thông tin nhân viên");
		thongTinNhanVien.showAndWait();


	}

	// Tien hanh tim kiem nhan vien
	private void timKiemNhanVien(String searchQuery) {
		if ("".equals(searchQuery)) {
			if (!(dsNhanVien_Temp.size() == dsNhanVien.size())) {
				dsNhanVien_Temp.clear();
				for (NhanVienForm nv : dsNhanVien)
					dsNhanVien_Temp.add(nv);
			}
		} else {
			dsNhanVien_Temp.clear();
			for (NhanVienForm nv : dsNhanVien) {
				if (nv.getMaNv().get().contains(searchQuery))
					dsNhanVien_Temp.add(nv);
			}

		}

	}

	// Tien hanh xoa nhan vien
	private void xoaNhanVien(int index) throws IOException {
		NhanVienForm nv = dsNhanVien_Temp.get(index);
		String id = nv.getMaNv().get();

		
		String url = "https://convenient-store.azurewebsites.net/api/staffs/" + id +"?accountId=1";
		Contants.deleteJson(url);
		
		Alert dialog = new Alert(AlertType.CONFIRMATION);
		dialog.setTitle("Thông báo");
		dialog.setHeaderText("Bạn đã xóa nhân viên " + nv.getTenMv().get());
		dialog.showAndWait();
		dsNhanVien_Temp.remove(index);
	}
}

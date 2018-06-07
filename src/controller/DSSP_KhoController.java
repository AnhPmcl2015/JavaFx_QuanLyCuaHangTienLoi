package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.json.JSONArray;
import org.json.JSONObject;

import contants.Contants;
import form.DSSP_KhoForm;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class DSSP_KhoController implements Initializable {

	private static ObservableList<DSSP_KhoForm> dsKho, dsKho_Temp;

	@FXML
	private ComboBox<String> cbbTimKiemTheo;

	@FXML
	private TextField txtMaSp;

	@FXML
	private Button btnXuatSanPham;

	@FXML
	private TableView<DSSP_KhoForm> tbDSSPKho;

	@FXML
	private TableColumn<DSSP_KhoForm, String> tcMaSp, tcTenSp, tcSoLuongTrongKho, tcSoLuongDangBan;

	@FXML
	private TableColumn<DSSP_KhoForm, ImageView> tcHinhAnh;

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
		if (dsKho == null) {
			dsKho = FXCollections.observableArrayList();
		} else {
			dsKho.clear();
		}

		if (dsKho_Temp == null) {
			dsKho_Temp = FXCollections.observableArrayList();
		} else {
			dsKho_Temp.clear();
		}

		tcHinhAnh.setCellValueFactory(new PropertyValueFactory<>("image"));
		tcMaSp.setCellValueFactory(cellData -> cellData.getValue().getMaSp());
		tcTenSp.setCellValueFactory(cellData -> cellData.getValue().getTenSp());
		tcSoLuongTrongKho.setCellValueFactory(cellData -> cellData.getValue().getSoLuongKho());
		tcSoLuongDangBan.setCellValueFactory(cellData -> cellData.getValue().getSoLuongBan());

		tbDSSPKho.setItems(dsKho_Temp);

		String url = "https://convenient-store.azurewebsites.net/api/productdetails?accountId=1";
		String json = Contants.getJSon(url);

		JSONObject objMain = new JSONObject(json);

		JSONArray array = objMain.getJSONArray("result");
		for (int i = 0; i < array.length(); i++) {
			JSONObject obj = array.getJSONObject(i);
			String maSp = obj.getLong("barCode") + "";

			// String imageUrl = Contants.USER_PATH + "sanPham/" + maSp + ".jpg";
			String imageUrl = "/resources/" + maSp + ".jpg";
			// File file = new File(imageUrl);
			// ImageView image = new ImageView(new Image(file.toURI().toString()));
			ImageView image = new ImageView(new Image(imageUrl));
			image.setFitHeight(100);
			image.setFitWidth(100);
			String soLuongKho = obj.getInt("quantityInRepository") + "";
			String soLuongBan = obj.getInt("quantityOnStore") + "";
			String tenSp = obj.getString("productName");

			dsKho.add(new DSSP_KhoForm(maSp, tenSp, soLuongKho, soLuongBan, image));
		}

		ObservableList<String> options = FXCollections.observableArrayList("Tất cả", "Mã sản phẩm", "Tên sản phẩm");
		cbbTimKiemTheo.getItems().addAll(options);
		cbbTimKiemTheo.getSelectionModel().select(0);
		for (DSSP_KhoForm ds : dsKho) {
			dsKho_Temp.add(ds);
		}
	}

	private void addEvents() {
		tbDSSPKho.setRowFactory(tv -> {
			TableRow<DSSP_KhoForm> row = new TableRow<>();
			row.setOnMouseClicked(e -> {
				if (e.getClickCount() == 2 && !(row.isEmpty())) {
					hienThiThongTinSanPham(row.getItem());
				}
			});
			return row;
		});

		btnXuatSanPham.setOnMouseClicked(e -> {
			hienThiBangXuatSanPham();
		});

		txtMaSp.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				timKiemSanPham(newValue);
			}
		});

		cbbTimKiemTheo.valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (cbbTimKiemTheo.getSelectionModel().getSelectedIndex() == 0) {
					txtMaSp.setDisable(true);
					txtMaSp.setVisible(false);
					dsKho_Temp.clear();
					for (DSSP_KhoForm ds : dsKho) {
						dsKho_Temp.add(ds);
					}
				} else {
					txtMaSp.setDisable(false);
					txtMaSp.setVisible(true);

				}

			}
		});
	}

	// Hien thi thong tin san pham
	private void hienThiThongTinSanPham(DSSP_KhoForm sp) {

	}

	// Tim kiem san pham
	private void timKiemSanPham(String searchQuery) {
		dsKho_Temp.clear();

		if (cbbTimKiemTheo.getSelectionModel().getSelectedIndex() == 1) {
			for (DSSP_KhoForm ds : dsKho) {
				if (ds.getMaSp().get().contains(searchQuery)) {
					dsKho_Temp.add(ds);
				}
			}
		} else if (cbbTimKiemTheo.getSelectionModel().getSelectedIndex() == 2) {
			for (DSSP_KhoForm ds : dsKho) {
				if (ds.getTenSp().get().contains(searchQuery)) {
					dsKho_Temp.add(ds);
				}
			}
		} else {
			for (DSSP_KhoForm ds : dsKho) {
				txtMaSp.setText("");
				dsKho_Temp.add(ds);
			}
		}
	}

	// Hien thi bang xuat san pham
	private void hienThiBangXuatSanPham() {

	}
}

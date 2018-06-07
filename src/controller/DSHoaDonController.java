package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.json.JSONArray;
import org.json.JSONObject;

import contants.Contants;
import form.DSHoaDonForm;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DSHoaDonController implements Initializable{
	
	private static ObservableList<DSHoaDonForm> dsHoaDon, dsHoaDon_Temp;
	
	@FXML
	private TextField txtMaHoaDon;
	
	@FXML
	private TableView<DSHoaDonForm> tbDsHoaDon;
	
	@FXML
	private TableColumn<DSHoaDonForm, String> tcMaHoaDon, tcMaNhanVien, tcNgayTao;
	
	
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
		if(dsHoaDon == null) {
			dsHoaDon = FXCollections.observableArrayList();
		}else {
			dsHoaDon.clear();
		}
		
		if(dsHoaDon_Temp == null) {
			dsHoaDon_Temp = FXCollections.observableArrayList();
		}else {
			dsHoaDon_Temp.clear();
		}
		
		tcMaHoaDon.setCellValueFactory(cellValue -> cellValue.getValue().getMaHoaDon());
		tcMaNhanVien.setCellValueFactory(cellValue -> cellValue.getValue().getTenNhanVien());
		tcNgayTao.setCellValueFactory(cellValue -> cellValue.getValue().getNgayLap());
		tbDsHoaDon.setItems(dsHoaDon_Temp);
		
		String url = "https://convenient-store.azurewebsites.net/api/bills?accountId=1";
		
		String json = Contants.getJSon(url);
		JSONArray array = new JSONArray(json);
		
		for(int i = 0; i < array.length(); i++) {
			JSONObject obj = array.getJSONObject(i);
			
			String billId = obj.getInt("billId") + "";
			String staffName = "The Anh";
			String createdDateTime = obj.getString("createdDateTime");
			createdDateTime = createdDateTime.substring(0, createdDateTime.indexOf("T"));
			String tongCong = "0";
			
			dsHoaDon.add(new DSHoaDonForm(billId, staffName, createdDateTime, tongCong));
		}
		
		for(DSHoaDonForm hd : dsHoaDon) {
			dsHoaDon_Temp.add(hd);
		}
	}
	
	private void addEvents() {
		tbDsHoaDon.setRowFactory(tv -> {
			TableRow<DSHoaDonForm> row = new TableRow<>();
			row.setOnMouseClicked(e -> {
				if (e.getClickCount() == 2 && !(row.isEmpty())) {
					try {
						hienThiThongTinHoaDon(row.getItem());
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			});

			return row;
		});
		
		txtMaHoaDon.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				timKiemHoaDon(newValue);
			}
		});
	}
	
	// hien thi thong tin hoa don
	private void hienThiThongTinHoaDon(DSHoaDonForm hoaDon) throws IOException {
		Contants.hoaDonChon = hoaDon.getMaHoaDon().get();
		
		Stage chiTietHoaDon = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/gui/ChiTietHoaDon.fxml"));
		Scene scene = new Scene(root, 1000, 554);
		chiTietHoaDon.setScene(scene);
		chiTietHoaDon.initModality(Modality.WINDOW_MODAL);
		chiTietHoaDon.setTitle("Chi Tiết hóa đơn");
		chiTietHoaDon.setResizable(false);
		chiTietHoaDon.showAndWait();
	}
	
	// tim kiem hoa don theo ma hoa don
	private void timKiemHoaDon(String newValue) {
		dsHoaDon_Temp.clear();
		if("".equals(newValue)) {
			for(DSHoaDonForm hd : dsHoaDon) {
				dsHoaDon_Temp.add(hd);
			}
		}else {
			for(DSHoaDonForm hd : dsHoaDon) {
				if(hd.getMaHoaDon().get().contains(newValue)) {
					dsHoaDon_Temp.add(hd);
				}
			}
		}
	}
}

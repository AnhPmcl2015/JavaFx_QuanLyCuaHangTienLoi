package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.json.JSONArray;
import org.json.JSONObject;

import contants.Contants;
import form.DSNCCForm;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class DSNhaCungCapController implements Initializable{
	private static ObservableList<DSNCCForm> dsNCC, dsNCC_Temp;
	
	@FXML
	private TextField txtMaNhaCungCap;
	
	@FXML
	private TableView<DSNCCForm> tvDsNhaCungCap;
	
	@FXML
	private TableColumn<DSNCCForm, String> tcMaNCC, tcTenNCC, tcDiaChi, tcSDT, tcEmail;
	
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
		if(dsNCC == null)
			dsNCC = FXCollections.observableArrayList();
		else
			dsNCC.clear();
		
		if(dsNCC_Temp == null)
			dsNCC_Temp = FXCollections.observableArrayList();
		else
			dsNCC_Temp.clear();
		
		tcMaNCC.setCellValueFactory(cellValue -> cellValue.getValue().getMaNCC());
		tcTenNCC.setCellValueFactory(cellValue -> cellValue.getValue().getTenNCC());
		tcDiaChi.setCellValueFactory(cellValue -> cellValue.getValue().getDiaChi());
		tcSDT.setCellValueFactory(cellValue -> cellValue.getValue().getSdt());
		tcEmail.setCellValueFactory(cellValue -> cellValue.getValue().getEmail());
		tvDsNhaCungCap.setItems(dsNCC_Temp);
		
		String url = "https://convenient-store.azurewebsites.net/api/suppliers";
		String json = Contants.getJSon(url);
		
		JSONArray array = new JSONArray(json);
		for(int i = 0; i < array.length(); i++) {
			JSONObject obj = array.getJSONObject(i);
			String maNCC = obj.getInt("supplierId")+"";
			String tenNCC = obj.getString("supplierName");
			String diaChi = obj.getString("address");
			String sdt = obj.getString("phoneNumber");
			String email = "haha@gmail.com";
			dsNCC.add(new DSNCCForm(maNCC, tenNCC, diaChi, sdt, email));
		}
		
		for(DSNCCForm ncc : dsNCC) {
			dsNCC_Temp.add(ncc);
		}
	}
	
	private void addEvents() {
		tvDsNhaCungCap.setRowFactory(tv -> {
			TableRow<DSNCCForm> row = new TableRow<>();
			row.setOnMouseClicked(e -> {
				if (e.getClickCount() == 2 && !(row.isEmpty())) {
					hienThiThongTinNCC(row.getItem());
				}
			});

			return row;
		});
		
		txtMaNhaCungCap.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				timKiemNhaCungCap(newValue);
			}
		});
	}
	
	// Hien thi thong tin chi tiet nha cung cap
	private void hienThiThongTinNCC(DSNCCForm ncc) {
		
	}

	private void timKiemNhaCungCap(String newValue) {
		dsNCC_Temp.clear();
		if("".equals(newValue)) {
			for(DSNCCForm ncc : dsNCC) {
				dsNCC_Temp.add(ncc);
			}
		}else {
			for(DSNCCForm ncc : dsNCC) {
				if(ncc.getMaNCC().get().contains(newValue)) {
					dsNCC_Temp.add(ncc);
				}
			}
		}
	}
}

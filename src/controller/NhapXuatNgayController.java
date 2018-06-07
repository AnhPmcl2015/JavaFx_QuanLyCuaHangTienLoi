package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.json.JSONArray;
import org.json.JSONObject;

import contants.Contants;
import form.NhapXuatNgayForm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;

public class NhapXuatNgayController implements Initializable{
private static ObservableList<NhapXuatNgayForm> dsNhap, dsXuat;
	
	@FXML
	private Text txtNhap, txtXuat;
	
	@FXML
	private TableView<NhapXuatNgayForm> tvNhap, tvXuatHang;
	
	@FXML
	private TableColumn<NhapXuatNgayForm, String> tcMaNhap, tcSoLuongNhap, tcMaXuat, tcSoLuongXuat;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			addControls();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void addControls() throws IOException {
		if(dsNhap == null) {
			dsNhap = FXCollections.observableArrayList();
		}else
			dsNhap.clear();
		
		if(dsXuat == null)
			dsXuat = FXCollections.observableArrayList();
		else
			dsXuat.clear();
		
		tcMaNhap.setCellValueFactory(cellValue -> cellValue.getValue().getMa());
		tcSoLuongNhap.setCellValueFactory(cellValue -> cellValue.getValue().getSoLuong());
		tcMaXuat.setCellValueFactory(cellValue -> cellValue.getValue().getMa());
		tcSoLuongXuat.setCellValueFactory(cellValue -> cellValue.getValue().getSoLuong());
		tvNhap.setItems(dsNhap);
		tvXuatHang.setItems(dsXuat);
		
		String url = "https://convenient-store.azurewebsites.net/api/reports/importexport?time=" + Contants.ngay_DoanhThu +"&timespan=d&productId=1";
		String json = Contants.getJSon(url);
		JSONObject objMain = new JSONObject(json);
		
		JSONArray arrayNhap = objMain.getJSONArray("importList");
		for(int i = 0; i < arrayNhap.length();i++) {
			JSONObject obj = arrayNhap.getJSONObject(i);
			String ma = obj.getString("name")+"";
			String soLuong = obj.getInt("productQuantity")+"";
			dsNhap.add(new NhapXuatNgayForm(ma, soLuong));
		}
		
		JSONArray arrayXuat = objMain.getJSONArray("exportList");
		for(int i = 0; i < arrayXuat.length(); i++) {
			JSONObject obj = arrayXuat.getJSONObject(i);
			String ma = obj.getString("name") +"";
			String soLuong = obj.getLong("exportedQuantity") +"";
			dsXuat.add(new NhapXuatNgayForm(ma, soLuong));
		}
		
		String imp = objMain.getInt("imp") + "";
		String exp = objMain.getInt("exp") + "";
		txtNhap.setText(imp);
		txtXuat.setText(exp);
	
	}
	

}

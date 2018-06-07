package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.json.JSONArray;
import org.json.JSONObject;

import contants.Contants;
import form.ChiTietHoaDonForm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;

public class ChiTietHoaDonController implements Initializable{
	private static ObservableList<ChiTietHoaDonForm> dsHoaDon;
	
	@FXML
	private Text txtMaHoaDon, txtNhanVien, txtTongCong;
	
	@FXML
	private TableView<ChiTietHoaDonForm> tbHoaDon;
	
	@FXML
	private TableColumn<ChiTietHoaDonForm, String> tcBarCode, tcTenSanPham, tcDVT, tcGia, tcSoLuong, tcThanhTien;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			addControls();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void addControls() throws IOException {
		if(dsHoaDon == null)
			dsHoaDon = FXCollections.observableArrayList();
		else
			dsHoaDon.clear();
		
		tcBarCode.setCellValueFactory(cellValue -> cellValue.getValue().getBarCode());
		tcTenSanPham.setCellValueFactory(cellValue -> cellValue.getValue().getTenSp());
		tcDVT.setCellValueFactory(cellValue -> cellValue.getValue().getDvt());
		tcGia.setCellValueFactory(cellValue -> cellValue.getValue().getGia());
		tcSoLuong.setCellValueFactory(cellValue -> cellValue.getValue().getSoLuong());
		tcThanhTien.setCellValueFactory(cellValue -> cellValue.getValue().getThanhTien());
		tbHoaDon.setItems(dsHoaDon);
		
		String url ="https://convenient-store.azurewebsites.net/api/bills/" +Contants.hoaDonChon+ "?accountId=1";
		String json = Contants.getJSon(url);
		
		JSONObject objMain = new JSONObject(json);
		String billId = objMain.getInt("billId") +"";
		String staffName = objMain.getString("staffName");
		String total = objMain.getLong("totalPrice") +"";
		txtMaHoaDon.setText(billId);
		txtNhanVien.setText(staffName);
		txtTongCong.setText(total);
		
		JSONArray array = objMain.getJSONArray("billDetails");
		for(int i = 0; i < array.length(); i++) {
			JSONObject obj = array.getJSONObject(i);
			String soLuong = obj.getInt("quantity") +"";
			String thanhTien = obj.getLong("subTotal")+"";
			
			JSONObject obj1 = obj.getJSONObject("productDetail");
			String barCode = obj1.getString("barCode");
			String tenSp = obj1.getString("productName");
			String dvt = "Cái";
			String gia = obj1.getLong("price")+"";
			dsHoaDon.add(new ChiTietHoaDonForm(barCode, tenSp, dvt, gia, soLuong, thanhTien));
		}
		
	}

}

package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.json.JSONArray;
import org.json.JSONObject;

import contants.Contants;
import form.BaoCaoNgayForm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;

public class DoanhThuNgayController implements Initializable{
	private static ObservableList<BaoCaoNgayForm> dsThu, dsChi;
	
	@FXML
	private Text txtTongThu, txtTongChi, txtDoanhThu;
	
	@FXML
	private TableView<BaoCaoNgayForm> tvChiTieu, tvThuNhap;
	
	@FXML
	private TableColumn<BaoCaoNgayForm, String> tcMaHoaDon, tcThanhTien, tcMaNhapHang, tcChiPhi;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			addControls();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		addEvents();
	}
	
	private void addControls() throws IOException {
		if(dsChi == null) {
			dsChi = FXCollections.observableArrayList();
		}else
			dsChi.clear();
		
		if(dsThu == null)
			dsThu = FXCollections.observableArrayList();
		else
			dsThu.clear();
		
		tcMaHoaDon.setCellValueFactory(cellValue -> cellValue.getValue().getMa());
		tcThanhTien.setCellValueFactory(cellValue -> cellValue.getValue().getTien());
		tcMaNhapHang.setCellValueFactory(cellValue -> cellValue.getValue().getMa());
		tcChiPhi.setCellValueFactory(cellValue -> cellValue.getValue().getTien());
		tvThuNhap.setItems(dsThu);
		tvChiTieu.setItems(dsChi);
		
		String url = "https://convenient-store.azurewebsites.net/api/reports/revenue?time=" + Contants.ngay_DoanhThu +"&timespan=d";
		String json = Contants.getJSon(url);
		JSONObject objMain = new JSONObject(json);
		JSONArray arrayBill = objMain.getJSONArray("bills");
		for(int i = 0; i < arrayBill.length();i++) {
			JSONObject obj = arrayBill.getJSONObject(i);
			String ma = obj.getInt("billId") +"";
			String tien = obj.getLong("sumPerBill") +"";
			dsThu.add(new BaoCaoNgayForm(ma, tien));
		}
		
		JSONArray arrayDelivery = objMain.getJSONArray("delivery");
		for(int i = 0; i < arrayDelivery.length(); i++) {
			JSONObject obj = arrayDelivery.getJSONObject(i);
			String ma = obj.getInt("deliveryId") +"";
			String tien = obj.getLong("cost") +"";
			dsChi.add(new BaoCaoNgayForm(ma, tien));
		}
		
		String in = objMain.getLong("_in") + "";
		String out = objMain.getLong("_out") + "";
		txtTongThu.setText(in);
		txtTongChi.setText(out);
		
		String doanhThu = objMain.getLong("revenue")+"";
		txtDoanhThu.setText(doanhThu);
	}
	
	private void addEvents() {
		
	}

}

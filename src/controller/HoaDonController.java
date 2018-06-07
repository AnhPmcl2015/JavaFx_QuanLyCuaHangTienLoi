package controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import org.json.JSONArray;
import org.json.JSONObject;

import contants.Contants;
import form.HoaDonForm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;

public class HoaDonController implements Initializable {

	private static ObservableList<HoaDonForm> listSanPham;
	private static double total = 0d;
	
	@FXML
	private TableView<HoaDonForm> tvHoaDon;

	@FXML
	private TableColumn<HoaDonForm, String> tcMaSp, tcTenSp, tcSoLuong, tcDonGia, tcDonViTinh, tcThanhTien;

	@FXML
	private TextField txtMaSP, txtSoLuong;

	@FXML
	private Text txtTongCong;

	@FXML
	private Button btnDongY;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		addControls();
		addEvents();
	}

	private void addControls() {
		if(listSanPham == null)
			listSanPham = FXCollections.observableArrayList();
		
		tcMaSp.setCellValueFactory(cellData->cellData.getValue().getMaSP());
		tcTenSp.setCellValueFactory(cellData->cellData.getValue().getTenSP());
		tcSoLuong.setCellValueFactory(cellData->cellData.getValue().getSoLuong());
		tcDonGia.setCellValueFactory(cellData->cellData.getValue().getDonGia());
		tcDonViTinh.setCellValueFactory(cellData->cellData.getValue().getDonViTinh());
		tcThanhTien.setCellValueFactory(cellData->cellData.getValue().getThanhTien());
		tvHoaDon.setItems(listSanPham);
		
		txtTongCong.setText("");
		txtMaSP.requestFocus();
		txtTongCong.setText("0");
	}

	private void addEvents() {
		txtMaSP.addEventHandler(KeyEvent.KEY_PRESSED, e->{
			if(e.getCode() == KeyCode.ENTER) {
				txtSoLuong.setText("");
				txtSoLuong.requestFocus();
			}
		});
		
		txtSoLuong.addEventHandler(KeyEvent.KEY_PRESSED, e->{
			if(e.getCode() == KeyCode.ENTER) {
				fillTableHoaDon(txtMaSP.getText());
			}
		});
		
		btnDongY.setOnMouseClicked(e->{
			capNhatDanhSachHoaDon();
		});
	}

	/**
	 * Khi nhan enter thi cap nhat gia tri va danh sach cac san pham da mua
	 */
	private void fillTableHoaDon(String maSp) {
		String url = "https://convenient-store.azurewebsites.net/api/productdetails?accountId=1&searchQuery="+maSp;
		try {
			String json = Contants.getJSon(url);
			JSONObject obj = new JSONObject(json);
			JSONArray array = (JSONArray) obj.get("result");
			
			if(array.length() == 0)
			{
				txtMaSP.setText("");
				txtSoLuong.setText("");
				txtMaSP.requestFocus();
				return;
			}
			
			
			JSONObject result = array.getJSONObject(0);
			String ma = result.getLong("barCode") +"";
			String tenSp = result.getString("productName");
			String soLuong = txtSoLuong.getText();
			String donGia = result.getLong("price")+"";
			String donViTinh = result.getString("unit");
			String thanhTien = (Float.parseFloat(soLuong) * Long.parseLong(donGia))+ "";
			HoaDonForm hoaDon = new HoaDonForm(ma, tenSp, soLuong, donGia, donViTinh, thanhTien);
			listSanPham.add(hoaDon);
			txtMaSP.setText("");
			txtSoLuong.setText("");
			txtMaSP.requestFocus();
			
			total+= Double.parseDouble(thanhTien);
			txtTongCong.setText(total+"");
		}catch(Exception e) {
			
		}
	}
	
	
	/**
	 * Cap nhat danh sach hoa don mua hang
	 */
	private void capNhatDanhSachHoaDon() {
		if(listSanPham == null || listSanPham.size() == 0) {
			Alert dialog = new Alert(AlertType.ERROR);
			dialog.setTitle("Lỗi");
			dialog.setHeaderText("Chưa có sản phẩm nào được mua!");
			dialog.showAndWait();
			return;
		}
		
		JSONObject hoaDonJSon = new JSONObject();
		hoaDonJSon.put("staffid", Contants.staffId+"");
		LocalDateTime dateTime = LocalDateTime.now();
		hoaDonJSon.put("createddatetime", dateTime.toString());
		
		JSONArray array = new JSONArray();
		for(HoaDonForm hd : listSanPham) {
			JSONObject obj = new JSONObject();
			obj.put("barcode",hd.getMaSP().get());
			obj.put("quantity", hd.getSoLuong().get());
			array.put(obj);
		}
		
		hoaDonJSon.put("billdetails", array);
		System.out.println(hoaDonJSon.toString());
		
		try {
			Contants.postJson("https://convenient-store.azurewebsites.net/api/bills?accountId=3", hoaDonJSon.toString());
			listSanPham.clear();
			txtMaSP.setText("");
			txtSoLuong.setText("");
			
			Alert dialog = new Alert(AlertType.INFORMATION);
			dialog.setTitle("Hóa đơn");
			dialog.setHeaderText("Cập nhật hóa đơn thành công!");
			dialog.showAndWait();
			txtTongCong.setText("");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
}

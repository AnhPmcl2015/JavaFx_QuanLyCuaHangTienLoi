package controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import contants.Contants;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.HBox;

public class BaoCaoDoanhThuController implements Initializable{

	@FXML
	private ComboBox<String> cmbLuaChon;
	
	@FXML
	private DatePicker dtp;
	
	@FXML
	private Button btn;
	
	@FXML
	private  HBox hbox;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		addControls();
		addEvents();
	}
	
	private void addControls() {
		ObservableList<String> luaChon = FXCollections.observableArrayList("Ngày", "Tháng", "Năm");
		cmbLuaChon.getItems().addAll(luaChon);
		cmbLuaChon.getSelectionModel().select(0);
		
	}
	
	private void addEvents() {
		btn.setOnMouseClicked(e->{
			try {
				hienThiBaoCao();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
	}
	
	private void hienThiBaoCao() throws IOException {
		LocalDate date = dtp.getValue();
		if(date == null)
			return;
		
		String s = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		Contants.ngay_DoanhThu = s;
		
		hbox.getChildren().clear();
		HBox root = null;
		if(cmbLuaChon.getSelectionModel().getSelectedIndex() == 0) {
			root = FXMLLoader.load(getClass().getResource("/gui/DoanhThuNgay.fxml"));
			Contants.ngay_DoanhThu = "";
		}else if(cmbLuaChon.getSelectionModel().getSelectedIndex() == 1) {
			root = FXMLLoader.load(getClass().getResource("/gui/DoanhThuThang.fxml"));
			Contants.ngay_DoanhThu = "";
		}else {
			root = FXMLLoader.load(getClass().getResource("/gui/DoanhThuNam.fxml"));
			Contants.ngay_DoanhThu = "";
		}
		hbox.getChildren().add(root);
	}
}

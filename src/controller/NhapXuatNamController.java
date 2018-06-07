package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.json.JSONArray;
import org.json.JSONObject;

import contants.Contants;
import form.DoanhThuThangForm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;

public class NhapXuatNamController implements Initializable {
	private static ObservableList<DoanhThuThangForm> ds;

	@FXML
	private TableView<DoanhThuThangForm> tvThongKe;

	@FXML
	private TableColumn<DoanhThuThangForm, String> tcNam, tcImp, tcExp;

	@FXML
	private Text txtNhap, txtXuat;
	
	@FXML
	private BarChart<String, Number> barChart;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			addControls();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private void addControls() throws IOException {
		if (ds == null)
			ds = FXCollections.observableArrayList();
		else
			ds.clear();

		tcNam.setCellValueFactory(c -> c.getValue().getNgay());
		tcImp.setCellValueFactory(c -> c.getValue().getIn());
		tcExp.setCellValueFactory(c -> c.getValue().getOut());
		tvThongKe.setItems(ds);
		
		String url = "https://convenient-store.azurewebsites.net/api/reports/importexport?time=2018-05-18&timespan=y&productId=1";
		String json = Contants.getJSon(url);

		JSONObject objMain = new JSONObject(json);
		String in = objMain.getLong("imp") + "";
		String out = objMain.getLong("exp") + "";
		txtNhap.setText(out);
		txtXuat.setText(in);
		
		
		JSONArray array = objMain.getJSONArray("result");
		for (int i = 0; i < array.length(); i++) {
			JSONObject obj = array.getJSONObject(i);
			String ngay = obj.getInt("_month") + "";
			String _in = obj.getLong("impPerMonth") + "";
			String _out = obj.getLong("expPerMonth") + "";
			String _rev = "0";

			ds.add(new DoanhThuThangForm(ngay, _in, _out, _rev));
		}
		
		themDuLieuVaoBarChart();

	}
	
	private void themDuLieuVaoBarChart() {
		XYChart.Series<String, Number> data = new XYChart.Series<String, Number>();
		data.setName("Nhập hàng");
		
		XYChart.Series<String, Number> data1 = new XYChart.Series<String, Number>();
		data1.setName("Xuất hàng");
	       
		for(DoanhThuThangForm d : ds) {
			String a = d.getNgay().get();
			Long b = Long.parseLong(d.getIn().get().trim());
			data.getData().add(new XYChart.Data<String, Number>(a, b));
			Long c = Long.parseLong(d.getOut().get().trim());
			data1.getData().add(new XYChart.Data<String, Number>(a, c));
		}
		
		barChart.getData().add(data);
		barChart.getData().add(data1); 
	       
	}


}

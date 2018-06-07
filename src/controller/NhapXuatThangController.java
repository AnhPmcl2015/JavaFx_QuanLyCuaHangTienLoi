package controller;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
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
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;

public class NhapXuatThangController implements Initializable{
	private static ObservableList<DoanhThuThangForm> ds;

	@FXML
	private TableView<DoanhThuThangForm> tvThongKe;

	@FXML
	private TableColumn<DoanhThuThangForm, String> tcNgay, tcImp, tcExp;

	@FXML
	private Text txtNhap, txtXuat;

	@FXML
	private BarChart<String, Number> barChart;
	
	@FXML
	private LineChart<String, Number> lineChart;

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

		tcNgay.setCellValueFactory(c -> c.getValue().getNgay());
		tcImp.setCellValueFactory(c -> c.getValue().getIn());
		tcExp.setCellValueFactory(c -> c.getValue().getOut());
		tvThongKe.setItems(ds);

		String url = "https://convenient-store.azurewebsites.net/api/reports/importexport?time=2018-05-18&timespan=m&productId=1";
		String json = Contants.getJSon(url);

		JSONObject objMain = new JSONObject(json);
		String in = objMain.getInt("imp") + "";
		String out = objMain.getInt("exp") + "";
		txtNhap.setText(in);
		txtXuat.setText(out);

		JSONArray array = objMain.getJSONArray("result");
		for (int i = 0; i < array.length(); i++) {
			JSONObject obj = array.getJSONObject(i);
			String ngay = obj.getInt("_day") + "";
			String _in = obj.getLong("impPerDay") + "";
			String _out = obj.getLong("expPerDay") + "";
			String _rev = "0";

			ds.add(new DoanhThuThangForm(ngay, _in, _out, _rev));
		}

		try {
			themDuLieuVaoLineChart();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	private void themDuLieuVaoLineChart() throws ParseException {
		/*XYChart.Series<String, Number> data = new XYChart.Series<String, Number>();
		data.setName("Nhập hàng");
		
		XYChart.Series<String, Number> data1 = new XYChart.Series<String, Number>();
		data.setName("Xuất hàng");
	       
		for(DoanhThuThangForm d : ds) {
			String a = d.getNgay().get();
			Long b = Long.parseLong(d.getIn().get().trim());
			data.getData().add(new XYChart.Data<String, Number>(a, b));
			Long c = Long.parseLong(d.getOut().get().trim());
			data1.getData().add(new XYChart.Data<String, Number>(a, c));
		}
		
		barChart.getData().add(data);
		barChart.getData().add(data1);*/
		
		
		XYChart.Series<String, Number> data = new XYChart.Series<String, Number>();
		data.setName("Nhập hàng");
		
		XYChart.Series<String, Number> data1 = new XYChart.Series<String, Number>();
		data1.setName("Xuất hàng");

		for (DoanhThuThangForm d : ds) {

			String a = d.getNgay().get();
			long b = Long.parseLong(d.getIn().get().trim());
			data.getData().add(new XYChart.Data<String, Number>( a, b));
			
			long c = Long.parseLong(d.getOut().get().trim());
			data1.getData().add(new XYChart.Data<String, Number>( a, c));
		}
		
		lineChart.getData().add(data);
		lineChart.getData().add(data1);
		
	
	}

}

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

public class DoanhThuNamController implements Initializable{
	private static ObservableList<DoanhThuThangForm> ds;

	@FXML
	private TableView<DoanhThuThangForm> tvThongKe;

	@FXML
	private TableColumn<DoanhThuThangForm, String> tcNam, tcIn, tcOut, tcRev;

	@FXML
	private Text txtTongThu, txtTongChi, txtDoanhThu;
	
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
		tcIn.setCellValueFactory(c -> c.getValue().getIn());
		tcOut.setCellValueFactory(c -> c.getValue().getOut());
		tcRev.setCellValueFactory(c -> c.getValue().getRev());
		tvThongKe.setItems(ds);
		
		String url = "https://convenient-store.azurewebsites.net/api/reports/revenue?time="+Contants.ngay_DoanhThu+"&timespan=y";
		String json = Contants.getJSon(url);

		JSONObject objMain = new JSONObject(json);
		String in = objMain.getLong("_in") + "";
		String out = objMain.getLong("_out") + "";
		String rev = objMain.getLong("revenue") + "";
		txtTongChi.setText(out);
		txtTongThu.setText(in);
		txtDoanhThu.setText(rev);
		
		
		JSONArray array = objMain.getJSONArray("result");
		for (int i = 0; i < array.length(); i++) {
			JSONObject obj = array.getJSONObject(i);
			String ngay = obj.getInt("_month") + "";
			String _in = obj.getLong("inPerMonth") + "";
			String _out = obj.getLong("outPerMonth") + "";
			String _rev = obj.getLong("revPerMonth") + "";

			ds.add(new DoanhThuThangForm(ngay, _in, _out, _rev));
		}
		
		themDuLieuVaoBarChart();

	}
	
	private void themDuLieuVaoBarChart() {
		XYChart.Series<String, Number> data = new XYChart.Series<String, Number>();
		data.setName("Doanh thu theo năm");
	       
		for(DoanhThuThangForm d : ds) {
			String a = d.getNgay().get();
			Long b = Long.parseLong(d.getRev().get().trim());
			data.getData().add(new XYChart.Data<String, Number>(a, b));
		}
		
		barChart.getData().add(data);
	       
	       
	}

}

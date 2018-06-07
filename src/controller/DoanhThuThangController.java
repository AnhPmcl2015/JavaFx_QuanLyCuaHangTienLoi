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
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;

public class DoanhThuThangController implements Initializable {

	private static ObservableList<DoanhThuThangForm> ds;

	@FXML
	private TableView<DoanhThuThangForm> TvThongKe;

	@FXML
	private TableColumn<DoanhThuThangForm, String> tcNgay, tcIn, tcOut, tcRev;

	@FXML
	private Text txtTongThu, txtTongChi, txtDoanhThu;

	@FXML
	private LineChart<String, Long> lineChart;

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
		tcIn.setCellValueFactory(c -> c.getValue().getIn());
		tcOut.setCellValueFactory(c -> c.getValue().getOut());
		tcRev.setCellValueFactory(c -> c.getValue().getRev());
		TvThongKe.setItems(ds);

		String url = "https://convenient-store.azurewebsites.net/api/reports/revenue?time="+Contants.ngay_DoanhThu+"&timespan=m";
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
			String ngay = obj.getInt("_day") + "";
			String _in = obj.getLong("inPerDay") + "";
			String _out = obj.getLong("outPerDay") + "";
			String _rev = obj.getLong("revPerDay") + "";

			ds.add(new DoanhThuThangForm(ngay, _in, _out, _rev));
		}

		try {
			themDuLieuVaoLineChart();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void themDuLieuVaoLineChart() throws ParseException {

		XYChart.Series<String, Long> data = new XYChart.Series();
		data.setName("Doanh thu");

		for (DoanhThuThangForm d : ds) {

			String a = d.getNgay().get();
			long b = Long.parseLong(d.getRev().get().trim());
			data.getData().add(new XYChart.Data<String, Long>( a, b));
		}
		
		lineChart.getData().add(data);

	}

}

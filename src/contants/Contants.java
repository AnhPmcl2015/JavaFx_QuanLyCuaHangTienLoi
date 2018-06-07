package contants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import form.DSSP_KhoForm;
import form.NhanVienForm;


public final class Contants {
	public static int staffId;
	public static int accountID;
	public static String staffName;
	public static NhanVienForm nhanVienDuocChon;
	public static DSSP_KhoForm sanPhamKhoDuocChon;
	public final static String USER_PATH = System.getProperty("user.home") + "/ministop_resources/resources/";
	public static String hoaDonChon = "";
	public static String ngay_DoanhThu = "";
	
	public static String getJSon(String url) throws IOException {
		String ketQua = "";

		URL obj;
		obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), Charset.forName("UTF-8")));
		String input = "";
		StringBuffer response = new StringBuffer();
		while ((input = in.readLine()) != null) {
			response.append(input);
		}
		in.close();

		ketQua = response.toString();
		System.out.println(ketQua);

		return ketQua;
	}
	
	public static void postJson(String url, String jsonString) throws ClientProtocolException, IOException {
		StringEntity entity = new StringEntity(jsonString);
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpPost request = new HttpPost(url);
		request.setEntity(entity);
		request.setHeader("Content-Type", "application/json");
		HttpResponse response = httpClient.execute(request);
		System.out.println(response.getStatusLine().getStatusCode());
	}
	
	public static void deleteJson(String url) throws IOException {
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("DELETE");
		System.out.println(con.getResponseCode());
	}
}

package main;

import java.io.IOException;
import java.net.URL;
import java.util.Base64;
import java.util.Optional;
import java.util.ResourceBundle;

import org.json.JSONObject;

import contants.Contants;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainControler implements Initializable {

	@FXML
	private TextField txtUserName, txtPassword;

	@FXML
	private Button btnDangNhap, btnThoat;

	@FXML
	private Text txtLoiDangNhap;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		addControls();
		addEvents();
	}

	private void addControls() {

	}

	private void addEvents() {
		txtUserName.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
			if (e.getCode() == KeyCode.ENTER) {
				login();
			}
		});

		txtPassword.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
			if (e.getCode() == KeyCode.ENTER) {
				login();
			}
		});
	}

	@FXML
	public void dangNhap(ActionEvent e) {
		login();
	}

	@FXML
	public void thoat(ActionEvent e) {
		exit(e);
	}

	private void login() {
		String loiDangNhap = "";
		if ("".equals(txtUserName.getText())) {
			loiDangNhap += "Tên đăng nhập không được để trống\n";
		}
		if ("".equals(txtPassword.getText()))
			loiDangNhap += "Mật khẩu không được để trống\n";
		if (!("".equals(txtUserName.getText()) || "".equals(txtPassword.getText()))) {
			String base64 = txtUserName.getText().trim() + ":" + txtPassword.getText().trim();
			System.out.println(base64);
			base64 = Base64.getEncoder().encodeToString(base64.getBytes());

			String urlAccount = "https://convenient-store.azurewebsites.net/api/accounts?encodedStr=" + base64;
			try {
				String json = Contants.getJSon(urlAccount);
				JSONObject obj = new JSONObject(json);
				int staffId = obj.getInt("staffId");
				int accountId = obj.getInt("accountId");
				String fullName = obj.getString("fullName");
				Contants.staffId = staffId;
				Contants.accountID = accountId;
				Contants.staffName = fullName;

			} catch (Exception e1) {
				loiDangNhap += "Tài khoản chưa được đăng ký";
			}

		}

		if (!"".equals(loiDangNhap)) {
			txtLoiDangNhap.setText(loiDangNhap);
			txtUserName.setText("");
			txtPassword.setText("");
			txtUserName.requestFocus();
			return;
		}

		// Chuyen man hinh
		Stage stage = (Stage) btnDangNhap.getScene().getWindow();
		stage.close();

		Stage manHinhChinh = new Stage();
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/gui/MainScreen.fxml"));
			Scene scene = new Scene(root, 1300, 690);
			manHinhChinh.setScene(scene);
			manHinhChinh.initModality(Modality.APPLICATION_MODAL);
			manHinhChinh.show();
			
			manHinhChinh.setTitle("Ministop");
			
			manHinhChinh.setOnCloseRequest(e -> {
				Alert dialog = new Alert(AlertType.CONFIRMATION);
				dialog.setTitle("Thoát");
				dialog.setHeaderText("Bạn muốn thoát chương trình?");
				Optional<ButtonType> option = dialog.showAndWait();

				if (option.get() == ButtonType.OK) {
					Stage primaryStage = new Stage();
					Parent rootDangNhap = null;
					try {
						rootDangNhap = FXMLLoader.load(getClass().getResource("Main.fxml"));
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
					Scene sceneDangNhap = new Scene(rootDangNhap,300,165);
					primaryStage.setScene(sceneDangNhap);
					primaryStage.show();
					
					primaryStage.setTitle("Đăng nhập");
					
					primaryStage.setResizable(false);
					primaryStage.setMaximized(false);
					
				} else if (option.get() == ButtonType.CANCEL) {
					dialog.close();
					e.consume();
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void exit(ActionEvent e) {
		Platform.exit();
	}

}

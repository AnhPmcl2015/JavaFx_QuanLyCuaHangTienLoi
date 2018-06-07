package controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import contants.Contants;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class ManHinhChinhController implements Initializable {

	/**
	 * Khai bao
	 */
	@FXML
	private TabPane tab;
	private SingleSelectionModel<Tab> selectionModel;
	private static int flagForTab = -1;
	private static int flagForDSSP_DichVu, flagForHoaDon, flagForDSSP_Kho, flagForDatHang, flagForNhapHang,
			flagForDSNhanVien, flagForThemNhanVien, flagForDoanhThu, flagForNhapXuatHang, flagForDSHoaDon,
			flagForDSNhaCungCap;

	@FXML
	private Button btnDanhSachSanPham_DichVu, btnHoaDon_DichVu, btnDanhSachSanPham_QuanLyKho, btnDatHang_QuanLyKho,
			btnNhapHang_QuanLyKho, btnDanhSach_NhanVien, btnThemNhanVien, btnBaoCaoDoanhThu, btnBaoCaoNhapXuatHang,
			btnDanhSachHoaDon, btnDSNhaCungCap;

	@FXML
	private Text txtNgay, txtTenNhanVien;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		addControls();
		addEvents();
	}

	private void addControls() {
		// Khoi tao tat ca cac tab deu la -1 - nghia la chua tab nao duoc chon
		selectionModel = tab.getSelectionModel();
		flagForDSSP_DichVu = -1;
		flagForHoaDon = -1;
		flagForDSSP_Kho = -1;
		flagForDatHang = -1;
		flagForNhapHang = -1;
		flagForDSNhanVien = -1;
		flagForThemNhanVien = -1;
		flagForDoanhThu = -1;
		flagForNhapXuatHang = -1;
		flagForDSHoaDon = -1;
		flagForDSNhaCungCap = -1;

		// Tien hanh cap nhat ngay thang va ho ten nhan vien
		LocalDate localDate = LocalDate.now();
		String ngay = localDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		txtNgay.setText(ngay);

		txtTenNhanVien.setText(Contants.staffName);

		btnDanhSachSanPham_DichVu.setDisable(true);
		btnThemNhanVien.setDisable(true);
		btnDatHang_QuanLyKho.setDisable(true);
		btnNhapHang_QuanLyKho.setDisable(true);
		
		if (5==Contants.accountID) {
			btnDanhSachSanPham_QuanLyKho.setDisable(true);
			btnDatHang_QuanLyKho.setDisable(true);
			btnNhapHang_QuanLyKho.setDisable(true);
			btnThemNhanVien.setDisable(true);
			btnDanhSach_NhanVien.setDisable(true);
			btnBaoCaoDoanhThu.setDisable(true);
			btnBaoCaoNhapXuatHang.setDisable(true);
			btnDanhSachHoaDon.setDisable(true);
			btnDSNhaCungCap.setDisable(true);
		} else if (6==Contants.accountID) {
			btnDanhSachSanPham_DichVu.setDisable(true);
			btnHoaDon_DichVu.setDisable(true);
			btnThemNhanVien.setDisable(true);
			btnDanhSach_NhanVien.setDisable(true);
			btnBaoCaoDoanhThu.setDisable(true);
			btnBaoCaoNhapXuatHang.setDisable(true);
			btnDanhSachHoaDon.setDisable(true);
			btnDSNhaCungCap.setDisable(true);
		}

	}

	private void addEvents() {
		btnDanhSachSanPham_DichVu.setOnMouseClicked(e -> {
			try {
				hienThiDSSP_DichVu();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});

		btnDanhSachSanPham_DichVu.setOnMouseEntered(e -> {
			mouseEnter(e);
		});

		btnDanhSachSanPham_DichVu.setOnMouseExited(e -> {
			mouseExit(e);
		});

		btnHoaDon_DichVu.setOnMouseClicked(e -> {
			try {
				hienThiHoaDon();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});

		btnHoaDon_DichVu.setOnMouseEntered(e -> {
			mouseEnter(e);
		});

		btnHoaDon_DichVu.setOnMouseExited(e -> {
			mouseExit(e);
		});

		btnDanhSachSanPham_QuanLyKho.setOnMouseClicked(e -> {
			try {
				hienThiDSSP_Kho();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});

		btnDanhSachSanPham_QuanLyKho.setOnMouseEntered(e -> {
			mouseEnter(e);
		});

		btnDanhSachSanPham_QuanLyKho.setOnMouseExited(e -> {
			mouseExit(e);
		});

		btnDatHang_QuanLyKho.setOnMouseClicked(e -> {
			try {
				hienThiDatHang();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});

		btnDatHang_QuanLyKho.setOnMouseEntered(e -> {
			mouseEnter(e);
		});

		btnDatHang_QuanLyKho.setOnMouseExited(e -> {
			mouseExit(e);
		});
		
		btnNhapHang_QuanLyKho.setOnMouseClicked(e -> {
			try {
				hienThiNhapHang();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});

		btnNhapHang_QuanLyKho.setOnMouseEntered(e -> {
			mouseEnter(e);
		});

		btnNhapHang_QuanLyKho.setOnMouseExited(e -> {
			mouseExit(e);
		});
		
		btnDanhSach_NhanVien.setOnMouseClicked(e -> {
			try {
				hienThiDSNhanVien();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});

		btnDanhSach_NhanVien.setOnMouseEntered(e -> {
			mouseEnter(e);
		});

		btnDanhSach_NhanVien.setOnMouseExited(e -> {
			mouseExit(e);
		});
		
		btnThemNhanVien.setOnMouseClicked(e -> {
			try {
				hienThiThemNhanVien();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});

		btnThemNhanVien.setOnMouseEntered(e -> {
			mouseEnter(e);
		});

		btnThemNhanVien.setOnMouseExited(e -> {
			mouseExit(e);
		});
		
		btnBaoCaoDoanhThu.setOnMouseClicked(e -> {
			try {
				hienThiBaoCaoDoanhThu();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});

		btnBaoCaoDoanhThu.setOnMouseEntered(e -> {
			mouseEnter(e);
		});

		btnBaoCaoDoanhThu.setOnMouseExited(e -> {
			mouseExit(e);
		});
		
		btnBaoCaoNhapXuatHang.setOnMouseClicked(e -> {
			try {
				hienThiBaoCaoNhapXuat();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});

		btnBaoCaoNhapXuatHang.setOnMouseEntered(e -> {
			mouseEnter(e);
		});

		btnBaoCaoNhapXuatHang.setOnMouseExited(e -> {
			mouseExit(e);
		});
		
		btnDanhSachHoaDon.setOnMouseClicked(e -> {
			try {
				hienThiDSHoaDon();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});

		btnDanhSachHoaDon.setOnMouseEntered(e -> {
			mouseEnter(e);
		});

		btnDanhSachHoaDon.setOnMouseExited(e -> {
			mouseExit(e);
		});
		
		btnDSNhaCungCap.setOnMouseClicked(e -> {
			try {
				hienThiDSNhaCungCap();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});

		btnDSNhaCungCap.setOnMouseEntered(e -> {
			mouseEnter(e);
		});

		btnDSNhaCungCap.setOnMouseExited(e -> {
			mouseExit(e);
		});
		
	}

	/**
	 * Xu ly hien thi cac man hinh rieng biet len cac tab
	 * @throws IOException 
	 */
	
	private void hienThiDSNhaCungCap() throws IOException {
		if(flagForDSNhaCungCap == -1) {
			Tab tab1 = new Tab();
			tab1.setText("Danh sách hóa đơn");
			
			Parent root = FXMLLoader.load(getClass().getResource("/gui/DSNhaCungCap.fxml"));
			tab1.setContent(root);
			tab.getTabs().add(tab1);
			
			flagForTab++;
			flagForDSNhaCungCap = flagForTab;
			
			tab1.setOnCloseRequest(e->{
				flagForTab --;
				flagForDSNhaCungCap = -1;
			});
		}
		
		selectionModel.select(flagForDSNhaCungCap);
		
	}

	private void hienThiDSHoaDon() throws IOException {
		if(flagForDSHoaDon == -1) {
			Tab tab1 = new Tab();
			tab1.setText("Danh sách hóa đơn");
			
			Parent root = FXMLLoader.load(getClass().getResource("/gui/DSHoaDon.fxml"));
			tab1.setContent(root);
			tab.getTabs().add(tab1);
			
			flagForTab++;
			flagForDSHoaDon = flagForTab;
			
			tab1.setOnCloseRequest(e->{
				flagForTab --;
				flagForDSHoaDon = -1;
			});
		}
		
		selectionModel.select(flagForDSHoaDon);
		
	}

	private void hienThiBaoCaoNhapXuat() throws IOException {
		if(flagForNhapXuatHang == -1) {
			Tab tab1 = new Tab();
			tab1.setText("Báo cáo nhập xuất hàng");
			
			Parent root = FXMLLoader.load(getClass().getResource("/gui/BaoCaoNhapHang.fxml"));
			tab1.setContent(root);
			tab.getTabs().add(tab1);
			
			flagForTab++;
			flagForNhapXuatHang = flagForTab;
			
			tab1.setOnCloseRequest(e->{
				flagForTab --;
				flagForNhapXuatHang = -1;
			});
		}
		
		selectionModel.select(flagForNhapXuatHang);
		
		
	}

	private void hienThiBaoCaoDoanhThu() throws IOException {
		if(flagForDoanhThu == -1) {
			Tab tab1 = new Tab();
			tab1.setText("Báo cáo doanh thu");
			
			Parent root = FXMLLoader.load(getClass().getResource("/gui/BaoCaoDoanhThu.fxml"));
			tab1.setContent(root);
			tab.getTabs().add(tab1);
			
			flagForTab++;
			flagForDoanhThu = flagForTab;
			
			tab1.setOnCloseRequest(e->{
				flagForTab --;
				flagForDoanhThu = -1;
			});
		}
		
		selectionModel.select(flagForDoanhThu);
		
	}

	private void hienThiThemNhanVien() throws IOException {
		if(flagForThemNhanVien == -1) {
			Tab tab1 = new Tab();
			tab1.setText("Thêm nhân viên");
			
			Parent root = FXMLLoader.load(getClass().getResource("/gui/ThemNhanVien.fxml"));
			tab1.setContent(root);
			tab.getTabs().add(tab1);
			
			flagForTab++;
			flagForThemNhanVien = flagForTab;
			
			tab1.setOnCloseRequest(e->{
				flagForTab --;
				flagForThemNhanVien = -1;
			});
		}
		
		selectionModel.select(flagForThemNhanVien);
		
	}

	private void hienThiDSNhanVien() throws IOException {
		if(flagForDSNhanVien == -1) {
			Tab tab1 = new Tab();
			tab1.setText("Danh sách nhân viên");
			
			Parent root = FXMLLoader.load(getClass().getResource("/gui/DSNhanVien.fxml"));
			tab1.setContent(root);
			tab.getTabs().add(tab1);
			
			flagForTab++;
			flagForDSNhanVien = flagForTab;
			
			tab1.setOnCloseRequest(e->{
				flagForTab --;
				flagForDSNhanVien = -1;
			});
		}
		
		selectionModel.select(flagForDSNhanVien);
		
	}

	private void hienThiNhapHang() throws IOException {
		if(flagForNhapHang == -1) {
			Tab tab1 = new Tab();
			tab1.setText("Nhập hàng");
			
			Parent root = FXMLLoader.load(getClass().getResource("/gui/NhapHang.fxml"));
			tab1.setContent(root);
			tab.getTabs().add(tab1);
			
			flagForTab++;
			flagForNhapHang = flagForTab;
			
			tab1.setOnCloseRequest(e->{
				flagForTab --;
				flagForNhapHang = -1;
			});
		}
		
		selectionModel.select(flagForNhapHang);
		
	}

	private void hienThiDatHang() throws IOException {
		if(flagForDatHang == -1) {
			Tab tab1 = new Tab();
			tab1.setText("Đặt hàng");
			
			Parent root = FXMLLoader.load(getClass().getResource("/gui/DatSanPham.fxml"));
			tab1.setContent(root);
			tab.getTabs().add(tab1);
			
			flagForTab++;
			flagForDatHang = flagForTab;
			
			tab1.setOnCloseRequest(e->{
				flagForTab --;
				flagForDatHang = -1;
			});
		}
		
		selectionModel.select(flagForDatHang);
		
	}

	private void hienThiDSSP_Kho() throws IOException {
		if(flagForDSSP_Kho == -1) {
			Tab tab1 = new Tab();
			tab1.setText("Danh sách sản phẩm");
			
			Parent root = FXMLLoader.load(getClass().getResource("/gui/DSSP_Kho.fxml"));
			tab1.setContent(root);
			tab.getTabs().add(tab1);
			
			flagForTab++;
			flagForDSSP_Kho = flagForTab;
			
			tab1.setOnCloseRequest(e->{
				flagForTab --;
				flagForDSSP_Kho = -1;
			});
		}
		
		selectionModel.select(flagForDSSP_Kho);
		
	}

	private void hienThiHoaDon() throws IOException {
		if(flagForHoaDon == -1) {
			Tab tab1 = new Tab();
			tab1.setText("Hóa đơn");
			
			Parent root = FXMLLoader.load(getClass().getResource("/gui/HoaDon.fxml"));
			tab1.setContent(root);
			tab.getTabs().add(tab1);
			
			flagForTab++;
			flagForHoaDon = flagForTab;
			
			tab1.setOnCloseRequest(e->{
				flagForTab --;
				flagForHoaDon = -1;
			});
		}
		
		selectionModel.select(flagForHoaDon);
		
	}

	private void hienThiDSSP_DichVu() throws IOException {
		if(flagForDSSP_DichVu == -1) {
			Tab tab1 = new Tab();
			tab1.setText("Danh sách sản phẩm");
			
			Parent root = FXMLLoader.load(getClass().getResource("/gui/DSSP_DichVu.fxml"));
			tab1.setContent(root);
			tab.getTabs().add(tab1);
			
			flagForTab++;
			flagForDSSP_DichVu = flagForTab;
			
			tab1.setOnCloseRequest(e->{
				flagForTab --;
				flagForDSSP_DichVu = -1;
			});
		}
		
		selectionModel.select(flagForDSSP_DichVu);
	}

	/**
	 * Xu ly mouse hover qua tung button
	 * 
	 * @param e
	 */
	private void mouseEnter(MouseEvent e) {
		Button btn = (Button) e.getSource();
		btn.setCursor(Cursor.HAND);
		btn.setStyle("-fx-background-color: #00CCFF");
	}

	private void mouseExit(MouseEvent e) {
		Button btn = (Button) e.getSource();
		btn.setStyle("-fx-background-color: transparent");
	}

}

package entity;

import javafx.beans.property.SimpleStringProperty;

public class BillDetail {

	private SimpleStringProperty id, barCode, billId, quantity;

	public BillDetail(String id, String barCode, String billId, String quantity) {
		this.id = new SimpleStringProperty(id);
		this.barCode = new SimpleStringProperty(barCode);
		this.billId = new SimpleStringProperty(billId);
		this.quantity = new SimpleStringProperty(quantity);
	}

	public BillDetail() {
		this(null, null, null, null);
	}

	public SimpleStringProperty getId() {
		return id;
	}

	public SimpleStringProperty getBarCode() {
		return barCode;
	}

	public SimpleStringProperty getBillId() {
		return billId;
	}

	public SimpleStringProperty getQuantity() {
		return quantity;
	}

	public void setId(SimpleStringProperty id) {
		this.id = id;
	}

	public void setBarCode(SimpleStringProperty barCode) {
		this.barCode = barCode;
	}

	public void setBillId(SimpleStringProperty billId) {
		this.billId = billId;
	}

	public void setQuantity(SimpleStringProperty quantity) {
		this.quantity = quantity;
	}

}

package entity;

import javafx.beans.property.SimpleStringProperty;

public class Order {

	private SimpleStringProperty id, date, staffId;

	public Order(String id, String date, String staffId) {
		this.id = new SimpleStringProperty(id);
		this.date = new SimpleStringProperty(date);
		this.staffId = new SimpleStringProperty(staffId);
	}

	public Order() {
		this(null, null, null);
	}

	public SimpleStringProperty getId() {
		return id;
	}

	public SimpleStringProperty getDate() {
		return date;
	}

	public SimpleStringProperty getStaffId() {
		return staffId;
	}

	public void setId(SimpleStringProperty id) {
		this.id = id;
	}

	public void setDate(SimpleStringProperty date) {
		this.date = date;
	}

	public void setStaffId(SimpleStringProperty staffId) {
		this.staffId = staffId;
	}

}

package entity;


import javafx.beans.property.SimpleStringProperty;

public class Bill {

	private SimpleStringProperty id, staffId, createdDate;

	public Bill(String id, String staffId, String createdDate) {
		this.id = new SimpleStringProperty(id);
		this.staffId = new SimpleStringProperty(staffId);
		this.createdDate = new SimpleStringProperty(createdDate);
	}

	public Bill() {
		this(null, null, null);
	}

	public SimpleStringProperty getId() {
		return id;
	}

	public SimpleStringProperty getStaffId() {
		return staffId;
	}

	public SimpleStringProperty getCreatedDate() {
		return createdDate;
	}

	public void setId(SimpleStringProperty id) {
		this.id = id;
	}

	public void setStaffId(SimpleStringProperty staffId) {
		this.staffId = staffId;
	}

	public void setCreatedDate(SimpleStringProperty createdDate) {
		this.createdDate = createdDate;
	}
}

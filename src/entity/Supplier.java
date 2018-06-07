package entity;

import javafx.beans.property.SimpleStringProperty;

public class Supplier {

	private SimpleStringProperty id, name, address, phoneNumber, email;

	public Supplier(String id, String name, String address, String phoneNumber, String email) {
		super();
		this.id = new SimpleStringProperty(id);
		this.name = new SimpleStringProperty(name);
		this.address = new SimpleStringProperty(address);
		this.phoneNumber = new SimpleStringProperty(phoneNumber);
		this.email = new SimpleStringProperty(email);
	}

	public Supplier() {
		this(null, null, null, null, null);
	}

	public SimpleStringProperty getId() {
		return id;
	}

	public SimpleStringProperty getName() {
		return name;
	}

	public SimpleStringProperty getAddress() {
		return address;
	}

	public SimpleStringProperty getPhoneNumber() {
		return phoneNumber;
	}

	public SimpleStringProperty getEmail() {
		return email;
	}

	public void setId(SimpleStringProperty id) {
		this.id = id;
	}

	public void setName(SimpleStringProperty name) {
		this.name = name;
	}

	public void setAddress(SimpleStringProperty address) {
		this.address = address;
	}

	public void setPhoneNumber(SimpleStringProperty phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setEmail(SimpleStringProperty email) {
		this.email = email;
	}

}

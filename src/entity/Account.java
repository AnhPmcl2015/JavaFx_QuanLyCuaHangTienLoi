package entity;

import javafx.beans.property.SimpleStringProperty;

public class Account {

	private SimpleStringProperty id, password, userName;

	public Account(String id, String password, String userName) {
		this.id = new SimpleStringProperty(id);
		this.password = new SimpleStringProperty(password);
		this.userName = new SimpleStringProperty(userName);
	}

	public Account() {
		this(null, null, null);
	}

	public SimpleStringProperty getId() {
		return id;
	}

	public SimpleStringProperty getPassword() {
		return password;
	}

	public SimpleStringProperty getUserName() {
		return userName;
	}

	public void setId(SimpleStringProperty id) {
		this.id = id;
	}

	public void setPassword(SimpleStringProperty password) {
		this.password = password;
	}

	public void setUserName(SimpleStringProperty userName) {
		this.userName = userName;
	}

}

package entity;

import javafx.beans.property.SimpleStringProperty;

public class Category {

	private SimpleStringProperty id, name;

	public Category(String id, String name) {
		this.id = new SimpleStringProperty(id);
		this.name = new SimpleStringProperty(name);
	}

	public Category() {
		this(null, null);
	}

	public SimpleStringProperty getId() {
		return id;
	}

	public SimpleStringProperty getName() {
		return name;
	}

	public void setId(SimpleStringProperty id) {
		this.id = id;
	}

	public void setName(SimpleStringProperty name) {
		this.name = name;
	}

}

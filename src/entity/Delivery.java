package entity;

import javafx.beans.property.SimpleStringProperty;

public class Delivery {

	private SimpleStringProperty id, date, supplierId, cost;

	public Delivery(String id, String date, String supplierId, String cost) {
		this.id = new SimpleStringProperty(id);
		this.date = new SimpleStringProperty(date);
		this.supplierId = new SimpleStringProperty(supplierId);
		this.cost = new SimpleStringProperty(cost);
	}

	public Delivery() {
		this(null, null, null, null);
	}

	public SimpleStringProperty getId() {
		return id;
	}

	public SimpleStringProperty getDate() {
		return date;
	}

	public SimpleStringProperty getSupplierId() {
		return supplierId;
	}

	public SimpleStringProperty getCost() {
		return cost;
	}

	public void setId(SimpleStringProperty id) {
		this.id = id;
	}

	public void setDate(SimpleStringProperty date) {
		this.date = date;
	}

	public void setSupplierId(SimpleStringProperty supplierId) {
		this.supplierId = supplierId;
	}

	public void setCost(SimpleStringProperty cost) {
		this.cost = cost;
	}

}

package entity;

import javafx.beans.property.SimpleStringProperty;

public class Product {
	private SimpleStringProperty id, supplierId, categoryId, name, image, unit;

	public Product(String id, String supplierId, String categoryId, String name, String image, String unit) {
		this.id = new SimpleStringProperty(id);
		this.supplierId = new SimpleStringProperty(supplierId);
		this.categoryId = new SimpleStringProperty(categoryId);
		this.name = new SimpleStringProperty(name);
		this.image = new SimpleStringProperty(image);
		this.unit = new SimpleStringProperty(unit);
	}

	public Product() {
		this(null, null, null, null, null, null);
	}

	public SimpleStringProperty getId() {
		return id;
	}

	public SimpleStringProperty getSupplierId() {
		return supplierId;
	}

	public SimpleStringProperty getCategoryId() {
		return categoryId;
	}

	public SimpleStringProperty getName() {
		return name;
	}

	public SimpleStringProperty getImage() {
		return image;
	}

	public SimpleStringProperty getUnit() {
		return unit;
	}

	public void setId(SimpleStringProperty id) {
		this.id = id;
	}

	public void setSupplierId(SimpleStringProperty supplierId) {
		this.supplierId = supplierId;
	}

	public void setCategoryId(SimpleStringProperty categoryId) {
		this.categoryId = categoryId;
	}

	public void setName(SimpleStringProperty name) {
		this.name = name;
	}

	public void setImage(SimpleStringProperty image) {
		this.image = image;
	}

	public void setUnit(SimpleStringProperty unit) {
		this.unit = unit;
	}

}

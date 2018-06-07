package entity;

import javafx.beans.property.SimpleStringProperty;

public class OrderDetail {

	private SimpleStringProperty id, productId, orderId, quantity, received;

	public OrderDetail(String id, String productId, String orderId, String quantity, String received) {
		this.id = new SimpleStringProperty(id);
		this.productId = new SimpleStringProperty(productId);
		this.orderId = new SimpleStringProperty(orderId);
		this.quantity = new SimpleStringProperty(quantity);
		this.received = new SimpleStringProperty(received);
	}

	public OrderDetail() {
		this(null, null, null, null, null);
	}

	public SimpleStringProperty getId() {
		return id;
	}

	public SimpleStringProperty getProductId() {
		return productId;
	}

	public SimpleStringProperty getOrderId() {
		return orderId;
	}

	public SimpleStringProperty getQuantity() {
		return quantity;
	}

	public SimpleStringProperty getReceived() {
		return received;
	}

	public void setId(SimpleStringProperty id) {
		this.id = id;
	}

	public void setProductId(SimpleStringProperty productId) {
		this.productId = productId;
	}

	public void setOrderId(SimpleStringProperty orderId) {
		this.orderId = orderId;
	}

	public void setQuantity(SimpleStringProperty quantity) {
		this.quantity = quantity;
	}

	public void setReceived(SimpleStringProperty received) {
		this.received = received;
	}

}

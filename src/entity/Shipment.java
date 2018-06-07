package entity;

import javafx.beans.property.SimpleStringProperty;

public class Shipment {
	
	private SimpleStringProperty id, orderDetailId, deliveryId;

	public Shipment(String id, String orderDetailId, String deliveryId) {
		super();
		this.id = new SimpleStringProperty(id);
		this.orderDetailId = new SimpleStringProperty(orderDetailId);
		this.deliveryId = new SimpleStringProperty(deliveryId);
	}
	
	public Shipment() {
		this(null,null,null);
	}

	public SimpleStringProperty getId() {
		return id;
	}

	public SimpleStringProperty getOrderDetailId() {
		return orderDetailId;
	}

	public SimpleStringProperty getDeliveryId() {
		return deliveryId;
	}

	public void setId(SimpleStringProperty id) {
		this.id = id;
	}

	public void setOrderDetailId(SimpleStringProperty orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

	public void setDeliveryId(SimpleStringProperty deliveryId) {
		this.deliveryId = deliveryId;
	}
	
	
	
}	

package entity;

import javafx.beans.property.SimpleStringProperty;

public class ProductDetail {

	private SimpleStringProperty barCode, shipmentId, price, quantityOnShop, quantityInRepository, expirationDate;

	public ProductDetail(String barCode, String shipmentId, String price, String quantityOnShop,
			String quantityInRepository, String expirationDate) {
		super();
		this.barCode = new SimpleStringProperty(barCode);
		this.shipmentId = new SimpleStringProperty(shipmentId);
		this.price = new SimpleStringProperty(price);
		this.quantityOnShop = new SimpleStringProperty(quantityOnShop);
		this.quantityInRepository = new SimpleStringProperty(quantityInRepository);
		this.expirationDate = new SimpleStringProperty(expirationDate);
	}

	public ProductDetail() {
		this(null, null, null, null, null, null);
	}

	public SimpleStringProperty getBarCode() {
		return barCode;
	}

	public SimpleStringProperty getShipmentId() {
		return shipmentId;
	}

	public SimpleStringProperty getPrice() {
		return price;
	}

	public SimpleStringProperty getQuantityOnShop() {
		return quantityOnShop;
	}

	public SimpleStringProperty getQuantityInRepository() {
		return quantityInRepository;
	}

	public SimpleStringProperty getExpirationDate() {
		return expirationDate;
	}

	public void setBarCode(SimpleStringProperty barCode) {
		this.barCode = barCode;
	}

	public void setShipmentId(SimpleStringProperty shipmentId) {
		this.shipmentId = shipmentId;
	}

	public void setPrice(SimpleStringProperty price) {
		this.price = price;
	}

	public void setQuantityOnShop(SimpleStringProperty quantityOnShop) {
		this.quantityOnShop = quantityOnShop;
	}

	public void setQuantityInRepository(SimpleStringProperty quantityInRepository) {
		this.quantityInRepository = quantityInRepository;
	}

	public void setExpirationDate(SimpleStringProperty expirationDate) {
		this.expirationDate = expirationDate;
	}

}

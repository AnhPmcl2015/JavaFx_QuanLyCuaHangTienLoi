package entity;


import javafx.beans.property.SimpleStringProperty;

public class Staff {

	private SimpleStringProperty id, name, identityNumber, dateOfBirth, gender, imageUrl, phoneNumber, accountId;

	public Staff(String id, String name, String identityNumber, String dateOfBirth, String gender, String imageUrl,
			String phoneNumber, String accountId) {
		super();
		this.id = new SimpleStringProperty(id);
		this.name = new SimpleStringProperty(name);
		this.identityNumber = new SimpleStringProperty(identityNumber);
		this.dateOfBirth = new SimpleStringProperty(dateOfBirth);
		this.gender = new SimpleStringProperty(gender);
		this.imageUrl = new SimpleStringProperty(imageUrl);
		this.phoneNumber = new SimpleStringProperty(phoneNumber);
		this.accountId = new SimpleStringProperty(accountId);
	}

	public Staff() {
		this(null, null, null, null, null, null, null, null);
	}

	public SimpleStringProperty getId() {
		return id;
	}

	public SimpleStringProperty getName() {
		return name;
	}

	public SimpleStringProperty getIdentityNumber() {
		return identityNumber;
	}

	public SimpleStringProperty getDateOfBirth() {
		return dateOfBirth;
	}

	public SimpleStringProperty getGender() {
		return gender;
	}

	public SimpleStringProperty getImageUrl() {
		return imageUrl;
	}

	public SimpleStringProperty getPhoneNumber() {
		return phoneNumber;
	}

	public SimpleStringProperty getAccountId() {
		return accountId;
	}

	public void setId(SimpleStringProperty id) {
		this.id = id;
	}

	public void setName(SimpleStringProperty name) {
		this.name = name;
	}

	public void setIdentityNumber(SimpleStringProperty identityNumber) {
		this.identityNumber = identityNumber;
	}

	public void setDateOfBirth(SimpleStringProperty dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public void setGender(SimpleStringProperty gender) {
		this.gender = gender;
	}

	public void setImageUrl(SimpleStringProperty imageUrl) {
		this.imageUrl = imageUrl;
	}

	public void setPhoneNumber(SimpleStringProperty phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setAccountId(SimpleStringProperty accountId) {
		this.accountId = accountId;
	}

}

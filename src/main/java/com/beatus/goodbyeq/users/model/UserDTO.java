package com.beatus.goodbyeq.users.model;

public class UserDTO {

	private String email;
	private String phoneNumber;
	private String firstName;
	private String lastName;
	private String userID;
	private String password;
	private String socialIdentifier;
	private String gender;
	private String dateOfBirth;
	private String salt;
	private CoordinatesDTO userCoordinates;
	private AddressDTO addressDTO;
	
	public UserDTO(String userID,String firstName,String lastName,String gender,
			String dateOfBirth, String email, String password, String salt, 
			String phoneNumber, CoordinatesDTO userCoordinates, AddressDTO addressDTO){
		this.userID = userID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.email = email;
		this.password = password;
		this.salt = salt;
		this.phoneNumber = phoneNumber;
		this.userCoordinates = userCoordinates;
		this.addressDTO = addressDTO;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSocialIdentifier() {
		return socialIdentifier;
	}

	public void setSocialIdentifier(String socialIdentifier) {
		this.socialIdentifier = socialIdentifier;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public CoordinatesDTO getUserCoordinates() {
		return userCoordinates;
	}

	public void setUserCoordinates(CoordinatesDTO userCoordinates) {
		this.userCoordinates = userCoordinates;
	}

	public AddressDTO getAddressDTO() {
		return addressDTO;
	}

	public void setAddressDTO(AddressDTO addressDTO) {
		this.addressDTO = addressDTO;
	}	
}

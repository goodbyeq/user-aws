package com.beatus.goodbyeq.users.model;

public class CoordinatesDTO {

	String longitude;
	String latittude;

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatittude() {
		return latittude;
	}

	public void setLatittude(String latittude) {
		this.latittude = latittude;
	}

	public CoordinatesDTO(String latittude, String longitude) {
		this.latittude = latittude;
		this.longitude = longitude;
	}
	/*@Override
	public String toString() {
		return "Coordinates [longitude=" + longitude + ", latittude=" + latittude + "]";
	}*/

}

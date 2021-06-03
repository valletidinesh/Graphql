package com.learn.modals;

public class Location {
	private String locationId;
	private String country;
	private String postalCode;

	public String getLocationId() {
		return locationId;
	}

	public Location(String locationId, String country, String postalCode) {
		super();
		this.locationId = locationId;
		this.country = country;
		this.postalCode = postalCode;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
}

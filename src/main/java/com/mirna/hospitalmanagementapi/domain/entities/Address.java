package com.mirna.hospitalmanagementapi.domain.entities;

import com.mirna.hospitalmanagementapi.domain.dtos.AddressDTO;

import jakarta.persistence.Embeddable;

@Embeddable
public class Address {

	public Address() {
		
	}
	
	public Address(AddressDTO addressDTO) {
		this.street = addressDTO.street();
		this.neighborhood = addressDTO.neighborhood();
		this.zipCode = addressDTO.zipCode();
		this.city = addressDTO.city();
		this.state = addressDTO.state();
		this.additionalDetails = addressDTO.additionalDetails();
		this.houseNumber = addressDTO.houseNumber();
	}
	
	private String street;
	private String neighborhood;
	private String zipCode;
	private String city;
	private String state;
	private String additionalDetails;
	private String houseNumber;
	
	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getAdditionalDetails() {
		return additionalDetails;
	}

	public void setAdditionalDetails(String additionalDetails) {
		this.additionalDetails = additionalDetails;
	}

	public String getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}
	
}

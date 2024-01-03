package com.mirna.hospitalmanagementapi.domain.entities;

import com.mirna.hospitalmanagementapi.domain.dtos.AddressDTO;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class Address {

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
}

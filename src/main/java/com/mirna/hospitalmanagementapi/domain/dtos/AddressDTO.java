package com.mirna.hospitalmanagementapi.domain.dtos;

public record AddressDTO(String street, String neighborhood, String zipCode, String city, String state,
		String additionalDetails, String houseNumber) {
}

package com.mirna.hospitalmanagementapi.domain.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

/**
* Data transfer object used to transfer data that will be saved in a Address entity
* 
* @author Mirna Gama
* @version 1.0
*/
public record AddressDTO(
		
		@NotBlank(message="street cannot be blank")
		String street, 
		
		@NotBlank(message="neighborhood cannot be blank")
		String neighborhood, 
		
		@NotBlank(message="zipCode cannot be blank")
		@Pattern(regexp="\\d{8}", message="invalid format for zipCode")
		String zipCode, 
		
		@NotBlank(message="city cannot be blank")
		String city, 
		
		@NotBlank(message="state cannot be blank")
		String state,
		
		String additionalDetails, 
	
		String houseNumber) {
}

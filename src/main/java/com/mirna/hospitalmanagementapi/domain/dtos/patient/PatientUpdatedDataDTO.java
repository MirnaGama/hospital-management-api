package com.mirna.hospitalmanagementapi.domain.dtos.patient;

import com.mirna.hospitalmanagementapi.domain.dtos.AddressDTO;

import jakarta.validation.constraints.NotNull;

/**
* Data transfer object used to update allowed data in a Patient entity
* 
* @author Mirna Gama
* @version 1.0
*/
public record PatientUpdatedDataDTO (
		@NotNull(message="id cannot be null")
		Long id,
		String name, 
		String telephone, 
		AddressDTO address) {

}

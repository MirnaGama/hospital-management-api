package com.mirna.hospitalmanagementapi.domain.dtos.doctor;

import com.mirna.hospitalmanagementapi.domain.dtos.AddressDTO;

import jakarta.validation.constraints.NotNull;

/**
* Data transfer object used to update allowed data in a Doctor entity
* 
* @author Mirna Gama
* @version 1.0
*/
public record DoctorUpdatedDataDTO(
		@NotNull(message="id cannot be null")
		Long id,
		String name, 
		String telephone, 
		AddressDTO address) {

}

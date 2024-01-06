package com.mirna.hospitalmanagementapi.domain.dtos.doctor;

import com.mirna.hospitalmanagementapi.domain.dtos.AddressDTO;
import com.mirna.hospitalmanagementapi.domain.enums.Specialty;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

/**
* Data transfer object used to transfer data that will be saved in a Doctor entity
* 
* @author Mirna Gama
* @version 1.0
*/
public record DoctorDTO(
		
		@NotBlank(message="name cannot be blank")
		String name, 
		
		@NotBlank(message="email cannot be blank")
		@Email(message="invalid format for email")
		String email, 
		
		@NotBlank(message="crm cannot be blank")
		@Pattern(regexp = "\\d{4,6}", message="invalid format for crm")
		String crm, 
		
		@NotBlank(message="telephone cannot be blank")
		String telephone, 
		
		@NotNull(message="speciality cannot be null")
		@Valid
		Specialty specialty, 
		
		@NotNull(message="address cannot be null")
		@Valid
		AddressDTO address) {

}

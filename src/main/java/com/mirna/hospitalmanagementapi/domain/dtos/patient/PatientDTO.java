package com.mirna.hospitalmanagementapi.domain.dtos.patient;

import com.mirna.hospitalmanagementapi.domain.dtos.AddressDTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

/**
 * Data transfer object used to transfer data that will be saved in a Patient
 * entity
 * 
 * @author Mirna Gama
 * @version 1.0
 */
public record PatientDTO(
		@NotBlank(message = "name cannot be blank") 
		String name,

		@NotBlank(message = "email cannot be blank") 
		@Email(message = "invalid format for email") 
		String email,

		@NotBlank(message = "cpf cannot be blank") 
		@Pattern(regexp = "\\d{11}", message = "invalid format for cpf") 
		String cpf,

		@NotBlank(message = "telephone cannot be blank") 
		String telephone,

		@NotNull(message = "address cannot be null") 
		@Valid 
		AddressDTO address) {}

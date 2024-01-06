package com.mirna.hospitalmanagementapi.domain.dtos.patient;

import com.mirna.hospitalmanagementapi.domain.entities.Patient;

/**
* Data transfer object used to format and display only the public data allowed for Patient entity
* 
* @author Mirna Gama
* @version 1.0
*/
public record PatientPublicDataDTO (String name, String email, String cpf){
	
	public PatientPublicDataDTO(Patient patient){
		this(patient.getName(), patient.getEmail(), patient.getCpf());
	}
}

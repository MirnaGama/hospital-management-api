package com.mirna.hospitalmanagementapi.domain.dtos.doctor;

import com.mirna.hospitalmanagementapi.domain.entities.Doctor;
import com.mirna.hospitalmanagementapi.domain.enums.Specialty;

/**
* Data transfer object used to format and display only the public data allowed for Doctor entity
* 
* @author Mirna Gama
* @version 1.0
*/
public record DoctorPublicDataDTO(String name, String email, String crm, Specialty specialty){
	
	public DoctorPublicDataDTO(Doctor doctor){
		this(doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getSpecialty());
	}
}

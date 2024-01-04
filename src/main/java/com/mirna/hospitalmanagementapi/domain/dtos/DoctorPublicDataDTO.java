package com.mirna.hospitalmanagementapi.domain.dtos;

import com.mirna.hospitalmanagementapi.domain.entities.Doctor;
import com.mirna.hospitalmanagementapi.domain.enums.Specialty;

public record DoctorPublicDataDTO(String name, String email, String crm, Specialty specialty){
	
	public DoctorPublicDataDTO(Doctor doctor){
		this(doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getSpecialty());
	}
}

package com.mirna.hospitalmanagementapi.domain.dtos.consultation;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mirna.hospitalmanagementapi.domain.enums.Specialty;
import com.mirna.hospitalmanagementapi.domain.validators.consultation.constraints.ConsultationDateBusinessHours;
import com.mirna.hospitalmanagementapi.domain.validators.consultation.constraints.ConsultationDateScheduledInAdvance;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

/**
* Data transfer object used to transfer data that will be saved in a Consultation entity
* 
* @author Mirna Gama
* @version 1.0
*/
public record ConsultationDTO (

	Long doctorId,
	
	@NotNull(message="patient id cannot be null")
	Long patientId,
	
	@NotNull(message="consultation date cannot be null")
	@Future
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	@ConsultationDateScheduledInAdvance
	@ConsultationDateBusinessHours
	LocalDateTime consultationDate,
	
	Specialty specialty
) {
	
}

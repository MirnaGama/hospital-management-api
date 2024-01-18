package com.mirna.hospitalmanagementapi.domain.dtos.consultation;

import com.mirna.hospitalmanagementapi.domain.enums.ReasonCancellation;

import jakarta.validation.constraints.NotNull;

/**
* Data transfer object used to transfer data about cancellation that will be updated in a Consultation entity
* 
* @author Mirna Gama
* @version 1.0
*/
public record ConsultationCanceledDTO (
	@NotNull
	Long consultationId,
	
	@NotNull
	ReasonCancellation reasonCancellation
) { }

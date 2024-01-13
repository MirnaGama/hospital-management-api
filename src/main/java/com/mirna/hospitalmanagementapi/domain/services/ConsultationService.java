package com.mirna.hospitalmanagementapi.domain.services;

import com.mirna.hospitalmanagementapi.domain.dtos.consultation.ConsultationDTO;
import com.mirna.hospitalmanagementapi.domain.entities.Consultation;
import com.mirna.hospitalmanagementapi.domain.exceptions.ConsultationValidationException;

public interface ConsultationService {

	/**
	* Adds a new consultation to the repository.
	* 
	* @param consultationDTO A data transfer object representing a consultation to add.
	* @return The saved consultation if successful,  or throws an exception if there is an error.
	 * @throws ConsultationValidationException if there is a validation error
	*/
	public Consultation addConsultation(ConsultationDTO consultationDTO) throws ConsultationValidationException;
	
}

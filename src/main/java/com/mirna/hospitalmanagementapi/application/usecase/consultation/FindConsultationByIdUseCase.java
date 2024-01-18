package com.mirna.hospitalmanagementapi.application.usecase.consultation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mirna.hospitalmanagementapi.domain.entities.Consultation;
import com.mirna.hospitalmanagementapi.domain.repositories.ConsultationRepository;

/**
 * This class is used to execute the findById method from consultation repository
 * 
 * @author Mirna Gama
 * @version 1.0
 */
@Component
public class FindConsultationByIdUseCase {

	@Autowired
	private ConsultationRepository consultationRepository;
	
	/**
	 * Executes the findById method from Consultation repository
	 * 
	 * @param id A long representing the consultation's unique identifier
	 * 
	 * @return The corresponding consultation if successful, or null if it is non-existent
	 *
	 */
	public Consultation execute(Long id) {
		return this.consultationRepository.findById(id).orElse(null);
	}
}

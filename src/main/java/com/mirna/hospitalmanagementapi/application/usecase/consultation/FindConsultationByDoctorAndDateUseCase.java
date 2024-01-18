package com.mirna.hospitalmanagementapi.application.usecase.consultation;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mirna.hospitalmanagementapi.domain.entities.Consultation;
import com.mirna.hospitalmanagementapi.domain.repositories.ConsultationRepository;

/**
 * This class is used to execute the findConsultationByDoctorAndDate method from consultation repository
 * 
 * @author Mirna Gama
 * @version 1.0
 */
@Component
public class FindConsultationByDoctorAndDateUseCase {

	@Autowired
	private ConsultationRepository consultationRepository;
	
	/**
	 * Executes the findConsultationByDoctorAndDate method from Consultation repository
	 * 
	 * @param doctorId The doctor's id from the consultation
	 * @param consultationDate The date of the consultation
	 * @return The corresponding consultation if successful, or null if it is non-existent
	 *
	 */
	public Consultation execute(Long doctorId, LocalDateTime consultationDate) {
		return this.consultationRepository.findConsultationByDoctorAndDate(doctorId, consultationDate);
	}
}

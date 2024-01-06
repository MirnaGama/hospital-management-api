package com.mirna.hospitalmanagementapi.application.usecase.patient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mirna.hospitalmanagementapi.domain.entities.Patient;
import com.mirna.hospitalmanagementapi.domain.repositories.PatientRepository;

/**
 * This class is used to execute the save method from patient repository
 * 
 * @author Mirna Gama
 * @version 1.0
 */
@Component
public class SavePatientUseCase {

	@Autowired
	private PatientRepository patientRepository;
	
	/**
	 * Executes the save method from Patient repository
	 * 
	 * @param patient The Patient to be saved in the repository
	 * @return The saved patient if successful, or null if there is an error
	 *
	 */
	public Patient execute(Patient patient) {
        return this.patientRepository.save(patient);
    }
}

package com.mirna.hospitalmanagementapi.application.usecase.doctor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mirna.hospitalmanagementapi.domain.entities.Doctor;
import com.mirna.hospitalmanagementapi.domain.repositories.DoctorRepository;

/**
 * This class is used to execute the findById method 
 * 
 * @author Mirna Gama
 * @version 1.0
 */
@Component
public class FindDoctorByIdUseCase {

	@Autowired
	private DoctorRepository doctorRepository;
	
	/**
	 * Executes the findById method from Doctor repository
	 * 
	 * @param id A long representing the doctor's unique identifier
	 * 
	 * @return The corresponding doctor if successful, or null if it is non-existent
	 *
	 */
	public Doctor execute(Long id) {
		return doctorRepository.findById(id).orElse(null);
	}
}

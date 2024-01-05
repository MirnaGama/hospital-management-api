package com.mirna.hospitalmanagementapi.application.usecase.doctor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mirna.hospitalmanagementapi.domain.entities.Doctor;
import com.mirna.hospitalmanagementapi.domain.repositories.DoctorRepository;

/**
 * This class is used to execute the save method from doctor repository
 * 
 * @author Mirna Gama
 * @version 1.0
 */
@Component
public class SaveDoctorUseCase {

	@Autowired
	private DoctorRepository doctorRepository;
	
	/**
	 * Executes the save method from Doctor repository
	 * 
	 * @param doctor The Doctor to be saved in the repository
	 * @return The saved doctor if successful, or null if there is an error
	 *
	 */
	public Doctor execute(Doctor doctor) {
        return this.doctorRepository.save(doctor);
    }
}

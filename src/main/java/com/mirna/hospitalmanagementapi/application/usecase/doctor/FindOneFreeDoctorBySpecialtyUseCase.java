package com.mirna.hospitalmanagementapi.application.usecase.doctor;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mirna.hospitalmanagementapi.domain.entities.Doctor;
import com.mirna.hospitalmanagementapi.domain.enums.Specialty;
import com.mirna.hospitalmanagementapi.domain.repositories.DoctorRepository;

/**
 * This class is used to execute the findOneFreeDoctorBySpecialty method 
 * 
 * @author Mirna Gama
 * @version 1.0
 */
@Component
public class FindOneFreeDoctorBySpecialtyUseCase {

	@Autowired
	private DoctorRepository doctorRepository;
	
	/**
	 * Executes the findOneFreeDoctorBySpecialty method from Doctor repository
	 * 
	 * @param specialty Desired specialty for doctor search
	 * @param consultationDate Date to check if the doctor is free
	 * @return A random free doctor with the defined specialty if successful, or null if it is non-existent
	 *
	 */
	public Doctor execute(Specialty specialty, LocalDateTime consultationDate) {
		return doctorRepository.findOneFreeDoctorBySpecialty(specialty, consultationDate);
	}
}

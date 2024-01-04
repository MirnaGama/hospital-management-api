package com.mirna.hospitalmanagementapi.application.usecase.doctor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mirna.hospitalmanagementapi.domain.entities.Doctor;
import com.mirna.hospitalmanagementapi.domain.repositories.DoctorRepository;

/**
 * This class is used to execute the findAll method from doctor repository
 * 
 * @author Mirna Gama
 * @version 1.0
 */
@Component
public class FindAllDoctorsUseCase {

	@Autowired
	private DoctorRepository doctorRepository;
	
	/**
	 * Executes the findAll method from Doctor repository
	 * 
	 * @return The list of all stored doctors if successful, or null if there is an error
	 *
	 */
	public List<Doctor> execute() {
        return this.doctorRepository.findAll();
    }
}

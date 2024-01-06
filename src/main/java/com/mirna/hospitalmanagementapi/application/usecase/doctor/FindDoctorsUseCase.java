package com.mirna.hospitalmanagementapi.application.usecase.doctor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.mirna.hospitalmanagementapi.domain.entities.Doctor;
import com.mirna.hospitalmanagementapi.domain.repositories.DoctorRepository;

/**
 * This class is used to execute the findDoctorsByActiveTrue method from doctor repository
 * 
 * @author Mirna Gama
 * @version 1.0
 */
@Component
public class FindDoctorsUseCase {

	@Autowired
	private DoctorRepository doctorRepository;
	
	/**
	 * Executes the findDoctorsByActiveTrue method from Doctor repository
	 * 
	 * @param pageable Pagination information, such as size and page number
	 * 
	 * @return A paginated list with active stored doctors if successful, or null if there is an error
	 *
	 */
	public Page<Doctor> execute(Pageable pageable) {
        return this.doctorRepository.findDoctorsByActiveTrue(pageable);
    }
}

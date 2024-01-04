package com.mirna.hospitalmanagementapi.application.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mirna.hospitalmanagementapi.application.usecase.doctor.AddDoctorUseCase;
import com.mirna.hospitalmanagementapi.application.usecase.doctor.FindAllDoctorsUseCase;
import com.mirna.hospitalmanagementapi.domain.dtos.DoctorDTO;
import com.mirna.hospitalmanagementapi.domain.dtos.DoctorPublicDataDTO;
import com.mirna.hospitalmanagementapi.domain.entities.Doctor;
import com.mirna.hospitalmanagementapi.domain.services.DoctorService;

/**
 * This class is an implementation of the DoctorService interface.
 *
 * This class provides methods to perform operations on doctors
 *
 * @author Mirna Gama
 * @version 1.0
 */

@Service
public class DoctorServiceImpl implements DoctorService {

	@Autowired
	private AddDoctorUseCase addDoctor;
	
	@Autowired
	private FindAllDoctorsUseCase findAllDoctors;
	
	/**
	 * Adds a new doctor to the database.
	 *
	 * @param doctorDTO A data transfer object containing the data for Doctor
	 *                    entity.
	 * @return The saved doctor if successful, or null if there is an error.
	 */
	@Override
	public Doctor addDoctor(DoctorDTO doctorDTO) {
		Doctor doctor = new Doctor(doctorDTO);
		
		return addDoctor.execute(doctor);
	}

	/**
	 * Finds doctors from the database.
	 *
	 * @return A paginated list containing data transfer objects with doctors public information in the repository
	 */
	@Override
	public List<DoctorPublicDataDTO> findDoctors() {
		return findAllDoctors.execute().stream().map(DoctorPublicDataDTO::new).toList();
	}

}

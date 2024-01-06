package com.mirna.hospitalmanagementapi.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mirna.hospitalmanagementapi.application.usecase.patient.FindPatientByIdUseCase;
import com.mirna.hospitalmanagementapi.application.usecase.patient.FindPatientsUseCase;
import com.mirna.hospitalmanagementapi.application.usecase.patient.SavePatientUseCase;
import com.mirna.hospitalmanagementapi.domain.dtos.patient.PatientDTO;
import com.mirna.hospitalmanagementapi.domain.dtos.patient.PatientPublicDataDTO;
import com.mirna.hospitalmanagementapi.domain.entities.Patient;
import com.mirna.hospitalmanagementapi.domain.services.PatientService;
import com.mirna.hospitalmanagementapi.infra.handlers.EntityNotFoundErrorHandler;

import jakarta.persistence.EntityNotFoundException;

/**
 * This class is an implementation of the PatientService interface.
 *
 * This class provides methods to perform operations on patients
 *
 * @author Mirna Gama
 * @version 1.0
 */
@Service
public class PatientServiceImpl implements PatientService {

	@Autowired
	private SavePatientUseCase savePatient;
	
	@Autowired
	private FindPatientByIdUseCase findPatientById;
	
	@Autowired
	private FindPatientsUseCase findPatients;
	
	/**
	 * Adds a new patient to the database.
	 *
	 * @param patientDTO A data transfer object containing the data for Patient
	 *                    entity.
	 * @return The saved patient if successful, or null if there is an error.
	 */
	@Override
	public Patient addPatient(PatientDTO patientDTO) {
		Patient patient = new Patient(patientDTO);
		
		return this.savePatient.execute(patient);
	}

	/**
	 * Finds a stored patient by id.
	 * 
	 * @param id A long representing the patient's unique identifier
	 * @return The corresponding patient if successful, or throws an EntityNotFoundException if it is
	 *         non-existent.
	 * @throws EntityNotFoundException When patient with id provided is non-existent   
	 */
	@Override
	public Patient findPatientById(Long id) throws EntityNotFoundException {
		Patient patient = this.findPatientById.execute(id);
		
		if (patient == null) throw new EntityNotFoundException();
		
		return patient;
	}

	/**
	 * Finds patients from the database.
	 *
	 * @param pageable Pagination information, such as size and page number
	 * 
	 * @return A paginated sublist containing data transfer objects with patients public information in the repository
	 */
	@Override
	public Page<PatientPublicDataDTO> findPatients(Pageable pageable) {
		return findPatients.execute(pageable).map(PatientPublicDataDTO::new);
	}

}

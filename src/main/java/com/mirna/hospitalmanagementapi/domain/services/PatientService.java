package com.mirna.hospitalmanagementapi.domain.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mirna.hospitalmanagementapi.domain.dtos.patient.PatientDTO;
import com.mirna.hospitalmanagementapi.domain.dtos.patient.PatientPublicDataDTO;
import com.mirna.hospitalmanagementapi.domain.dtos.patient.PatientUpdatedDataDTO;
import com.mirna.hospitalmanagementapi.domain.entities.Patient;

/**
 * Patient service interface for managing patient information.
*
 * @see Patient
 * @author Mirna Gama
 * @version 1.0
*/
public interface PatientService {

	/**
	* Adds a new patient to the repository.
	* 
	* @param patientDTO A data transfer object representing a patient to add.
	* @return The saved patient if successful,  or null if there is an error.
	*/
    public Patient addPatient(PatientDTO patientDTO);
    
    /**
	* Finds a stored patient by id.
	* 
	* @param id A long representing the patient's unique identifier
	* @return The corresponding patient if successful, or null if it is non-existent.
	*/
    public Patient findPatientById(Long id);
    
    /**
	 * Retrieves a paginated sublist of doctors.
	 * 
	 * @param pageable Pagination information, such as size and page number
	 *  
	 * @return A paginated sublist containing data transfer objects with patients public information in the repository
	 */
    public Page<PatientPublicDataDTO> findPatients(Pageable pageable);
    
    /**
     * Updates an existing patient record
     * @param patientUpdatedDataDTO Data transfer object containing the patient updated data along with their corresponding id 
	 *  
	 * @return The updated patient if successful,  or null if there is an error.
	 */
    public Patient updatePatient(PatientUpdatedDataDTO patientUpdatedDataDTO);
    
    /**
     * Deactivates an existing patient record by provided id
     * @param id Long that represents the patient's unique identifier
	 *  
	 * @return The deactivated patient if successful,  or null if there is an error.
	 */
    public Patient deactivatePatient(Long id);
}

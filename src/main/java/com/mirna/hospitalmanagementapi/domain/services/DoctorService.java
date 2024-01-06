package com.mirna.hospitalmanagementapi.domain.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mirna.hospitalmanagementapi.domain.dtos.doctor.DoctorDTO;
import com.mirna.hospitalmanagementapi.domain.dtos.doctor.DoctorPublicDataDTO;
import com.mirna.hospitalmanagementapi.domain.dtos.doctor.DoctorUpdatedDataDTO;
import com.mirna.hospitalmanagementapi.domain.entities.Doctor;

/**
 * Doctor service interface for managing doctor information.
*
 * @see Doctor
 * @author Mirna Gama
 * @version 1.0
*/
public interface DoctorService {

	/**
	* Adds a new doctor to the repository.
	* 
	* @param doctorDTO A data transfer object representing a doctor to add.
	* @return The saved doctor if successful,  or null if there is an error.
	*/
    public Doctor addDoctor(DoctorDTO doctorDTO);

	/**
   	* Finds a stored doctor by id.
   	* 
   	* @param id A long representing the doctor's unique identifier
   	* @return The corresponding doctor if successful, or null if it is non-existent.
   	*/
    public Doctor findDoctorById(Long id);
	
    /**
	 * Retrieves a paginated sublist of doctors.
	 * 
	 * @param pageable Pagination information, such as size and page number
	 *  
	 * @return A paginated sublist containing data transfer objects with doctors public information in the repository
	 */
    public Page<DoctorPublicDataDTO> findDoctors(Pageable pageable);
    
    /**
     * Updates an existing doctor record
     * @param doctorUpdatedDataDTO Data transfer object containing the doctor updated data along with their corresponding id 
	 *  
	 * @return The updated doctor if successful,  or null if there is an error.
	 */
    public Doctor updateDoctor(DoctorUpdatedDataDTO doctorUpdatedDataDTO);
    
    /**
     * Deactivates an existing doctor record by provided id
     * @param id Long that represents the doctor's unique identifier
	 *  
	 * @return The deactivated doctor if successful,  or null if there is an error.
	 */
    public Doctor deactivateDoctor(Long id);
}

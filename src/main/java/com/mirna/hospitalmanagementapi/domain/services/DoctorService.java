package com.mirna.hospitalmanagementapi.domain.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mirna.hospitalmanagementapi.domain.dtos.DoctorDTO;
import com.mirna.hospitalmanagementapi.domain.dtos.DoctorPublicDataDTO;
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
	 * Retrieves a paginated sublist of doctors.
	 * 
	 * @param pageable Pagination information, such as size and page number
	 *  
	 * @return A paginated sublist containing data transfer objects with doctors public information in the repository
	 */
    public Page<DoctorPublicDataDTO> findDoctors(Pageable pageable);

}

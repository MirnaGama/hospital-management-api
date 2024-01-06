package com.mirna.hospitalmanagementapi.domain.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.mirna.hospitalmanagementapi.domain.entities.Patient;

/**
 * Repository interface for retrieving and manipulating all Patient objects using their unique Long identifier.
*
 * @author Mirna Gama
* @version 1.0
*/
public interface PatientRepository extends JpaRepository<Patient, Long>{

	/**
	 * 
	 * @param pageable Pagination information, such as size and page number
	 * @return A paginated list with active stored patients if successful, or null if there is an error
	 */
	Page<Patient> findPatientsByActiveTrue(Pageable pageable);

}

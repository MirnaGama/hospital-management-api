package com.mirna.hospitalmanagementapi.domain.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.mirna.hospitalmanagementapi.domain.entities.Doctor;

/**
 * Repository interface for retrieving and manipulating all Doctor objects using their unique Long identifier.
*
 * @author Mirna Gama
* @version 1.0
*/
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

	/**
	 * 
	 * @param pageable Pagination information, such as size and page number
	 * @return A paginated list with active stored doctors if successful, or null if there is an error
	 */
	Page<Doctor> findDoctorsByActiveTrue(Pageable pageable);
}

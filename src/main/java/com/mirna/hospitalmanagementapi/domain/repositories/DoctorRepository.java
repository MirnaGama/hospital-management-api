package com.mirna.hospitalmanagementapi.domain.repositories;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mirna.hospitalmanagementapi.domain.entities.Doctor;
import com.mirna.hospitalmanagementapi.domain.enums.Specialty;

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
	
	/**
	 * 
	 * @param specialty Desired specialty for doctor search
	 * @param consultationDate Date to check if the doctor is free
	 * @return A random free doctor with the defined specialty if successful, or null if it is non-existent
	 */
	@Query("""
			select d from Doctor d
			where d.active = true 
			and specialty = :specialty
			and d.id not in (
				select c.doctor.id from Consultation c
				where c.consultationDate = :consultationDate 
				and c.canceled = false
			)
			order by rand()
			limit 1
			""")
	Doctor findOneFreeDoctorBySpecialty(Specialty specialty, LocalDateTime consultationDate);
}

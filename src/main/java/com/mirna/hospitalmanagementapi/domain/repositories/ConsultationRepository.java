package com.mirna.hospitalmanagementapi.domain.repositories;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mirna.hospitalmanagementapi.domain.entities.Consultation;

/**
 * Repository interface for retrieving and manipulating all Consultation objects using their unique Long identifier.
*
 * @author Mirna Gama
* @version 1.0
*/

public interface ConsultationRepository extends JpaRepository<Consultation, Long> {

	/**
	 * 
	 * @param patientId The patient's id from the consultation
	 * @param consultationDate The date of the consultation
	 * @return The corresponding consultation if successful, or null if it is non-existent
	 */
	@Query("""
			select c from Consultation c
			where c.patient.id = :patientId
			and c.consultationDate = :consultationDate 
			and c.canceled = false
			""")
	Consultation findConsultationByPatientAndDate(Long patientId, LocalDateTime consultationDate);

	/**
	 * 
	 * @param doctorId The doctor's id from the consultation
	 * @param consultationDate The date of the consultation
	 * @return The corresponding consultation if successful, or null if it is non-existent
	 */
	@Query("""
			select c from Consultation c
			where c.doctor.id = :doctorId
			and c.consultationDate = :consultationDate 
			and c.canceled = false
			""")
	Consultation findConsultationByDoctorAndDate(Long doctorId, LocalDateTime consultationDate);

}

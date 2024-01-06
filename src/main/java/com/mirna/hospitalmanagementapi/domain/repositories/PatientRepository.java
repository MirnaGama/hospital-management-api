package com.mirna.hospitalmanagementapi.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mirna.hospitalmanagementapi.domain.entities.Patient;

/**
 * Repository interface for retrieving and manipulating all Patient objects using their unique Long identifier.
*
 * @author Mirna Gama
* @version 1.0
*/
public interface PatientRepository extends JpaRepository<Patient, Long>{

}

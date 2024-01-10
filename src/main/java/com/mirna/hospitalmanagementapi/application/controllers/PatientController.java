package com.mirna.hospitalmanagementapi.application.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.mirna.hospitalmanagementapi.domain.dtos.patient.PatientDTO;
import com.mirna.hospitalmanagementapi.domain.dtos.patient.PatientPublicDataDTO;
import com.mirna.hospitalmanagementapi.domain.dtos.patient.PatientUpdatedDataDTO;
import com.mirna.hospitalmanagementapi.domain.entities.Patient;
import com.mirna.hospitalmanagementapi.domain.services.PatientService;

import jakarta.validation.Valid;

/**
 * A Spring REST controller for managing patients.
 *
 * @author Mirna Gama
 * @version 1.0
 */
@RestController
@RequestMapping("/api/v1.0/patients")
public class PatientController {

	@Autowired
	private PatientService patientService;
	
	/**
	 * Post method to create a new Patient object based on the provided DTO.
	 *
	 * @param patientDTO The data transfer object containing data for Patient
	 * entity.
	 * 
	 * @return A response entity containing the saved patient and created status if successful, or
	 * a 400-level error if there is a validation error
	 */
	@PostMapping
	public ResponseEntity<Object> postPatient(@RequestBody @Valid PatientDTO patientDTO) {
	
		Patient patient = patientService.addPatient(patientDTO);
		
		UriComponents uriComponents = UriComponentsBuilder
				.fromUriString("/api/v1.0/patient/{id}") 
				.encode() 
				.build(); 

		URI uri = uriComponents.expand(patient.getId()).toUri();
		
		return ResponseEntity.created(uri).body(patient);
	}
	
	/**
	 * Get method to receive a Patient record by the provided ID
	 *
	 * @param id A long representing the patient's unique identifier
	 * 
	 * @return A response entity containing the saved patient if successful, or
	 * a 404 level error if it is non-existent
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Object> getPatient(@PathVariable Long id) {
	
		Patient patient = patientService.findPatientById(id);
		
		return ResponseEntity.ok(patient);
	}
	
	/**
	 * Get method to receive a paginated sublist of objects containing data transfer objects with Patient public information
	 *
	 * @param pageable Pagination information, such as size and page number
	 * 
	 * @return A response entity containing the paginated sublist of patients if successful
	 */
	@GetMapping
	public ResponseEntity<Object> getPatients(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable) {
		
		Page<PatientPublicDataDTO> patients = patientService.findPatients(pageable);
		
		return ResponseEntity.ok(patients);
	}
	
	/**
	 * Put method to update a existing patient record by provided ID
	 *
	 * @param patientUpdatedDataDTO Data Transfer Object containing allowed data to be updated in Patient entity
	 * 
	 * @return A response entity containing the saved patient if successful, or
	 * a 400-level error if there is a validation error
	 */
	@PutMapping
	public ResponseEntity<Object> putPatient(@RequestBody @Valid PatientUpdatedDataDTO patientUpdatedDataDTO) {
		
		Patient patient = patientService.updatePatient(patientUpdatedDataDTO);
		
		return ResponseEntity.ok(patient);
	}
	
	/**
	 * Delete method to deactivate an existing patient record by provided ID
	 *
	 * @param id Path variable that represents the patient's unique identifier
	 * 
	 * @return A response entity containing the deactivated patient if successful
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deletePatient(@PathVariable Long id) {
		Patient patient = patientService.deactivatePatient(id);

		return ResponseEntity.ok(patient);
	}
}

package com.mirna.hospitalmanagementapi.application.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.mirna.hospitalmanagementapi.domain.dtos.patient.PatientDTO;
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
}
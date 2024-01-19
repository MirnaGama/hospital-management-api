package com.mirna.hospitalmanagementapi.application.controllers;

import java.net.URI;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.mirna.hospitalmanagementapi.domain.dtos.consultation.ConsultationCanceledDTO;
import com.mirna.hospitalmanagementapi.domain.dtos.consultation.ConsultationDTO;
import com.mirna.hospitalmanagementapi.domain.entities.Consultation;
import com.mirna.hospitalmanagementapi.domain.exceptions.ConsultationValidationException;
import com.mirna.hospitalmanagementapi.domain.services.ConsultationService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

/**
 * A Spring REST controller for managing consultations.
 *
 * @author Mirna Gama
 * @version 1.0
 */
@RestController
@RequestMapping("/api/v1.0/consultations")
@SecurityRequirement(name = "bearer-key")
public class ConsultationController {

	@Autowired
	private ConsultationService consultationService;
	
	/**
	 * Post method to create a new Consultation object based on the provided DTO.
	 *
	 * @param consultationDTO The data transfer object containing data for Consultation
	 * entity.
	 * 
	 * @return A response entity containing the saved consultation and created status if successful, or
	 * a 400-level error if there is a validation error
	 * @throws ConsultationValidationException if there is a validation error
	 */
	@PostMapping
	public ResponseEntity<Object> postConsultation(@RequestBody @Valid ConsultationDTO consultationDTO) throws ConsultationValidationException {
		Consultation consultation = consultationService.addConsultation(consultationDTO);
		
		UriComponents uriComponents = UriComponentsBuilder
				.fromUriString("/api/v1.0/consultations/{id}") 
				.encode() 
				.build(); 

		URI uri = uriComponents.expand(consultation.getId()).toUri();
		
		return ResponseEntity.created(uri).body(consultation);
	}
	
	/**
	 * Get method to receive a Consultation record by the provided ID
	 *
	 * @param id A long representing the consultation's unique identifier
	 * 
	 * @return A response entity containing the corresponding consultation if successful, or
	 * a 400-level error if it is non-existent
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Object> getConsultation(@PathVariable Long id) {
		Consultation consultation = consultationService.findConsultationById(id);
		
		return ResponseEntity.ok(consultation);
	}
		
	/**
	 * Delete method to update a new Consultation object based on the provided DTO.
	 *
	 * @param consultationCanceledDTO The data transfer object containing data to update Consultation
	 * entity.
	 * 
	 * @return A response entity containing the canceled consultation and ok status if successful, or
	 * a 400-level error if the consultation entity is not found
	 */
	@DeleteMapping
	public ResponseEntity<Object> deleteConsultation(@RequestBody @Valid ConsultationCanceledDTO consultationCanceledDTO) {
		return ResponseEntity.ok(consultationService.cancelConsultation(consultationCanceledDTO));
	}
}

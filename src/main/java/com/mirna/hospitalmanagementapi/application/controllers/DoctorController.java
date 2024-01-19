package com.mirna.hospitalmanagementapi.application.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.mirna.hospitalmanagementapi.domain.dtos.doctor.DoctorDTO;
import com.mirna.hospitalmanagementapi.domain.dtos.doctor.DoctorPublicDataDTO;
import com.mirna.hospitalmanagementapi.domain.dtos.doctor.DoctorUpdatedDataDTO;
import com.mirna.hospitalmanagementapi.domain.entities.Doctor;
import com.mirna.hospitalmanagementapi.domain.entities.Patient;
import com.mirna.hospitalmanagementapi.domain.services.DoctorService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

/**
 * A Spring REST controller for managing doctors.
 *
 * @author Mirna Gama
 * @version 1.0
 */
@RestController
@RequestMapping("/api/v1.0/doctors")
@SecurityRequirement(name = "bearer-key")
public class DoctorController {

	@Autowired
	private DoctorService doctorService;
	
	/**
	 * Post method to create a new Doctor object based on the provided DTO.
	 *
	 * @param doctorDTO The data transfer object containing data for Doctor
	 * entity.
	 * 
	 * @return A response entity containing the saved doctor and created status if successful, or
	 * a 400-level error if there is a validation error
	 */
	@PostMapping
	public ResponseEntity<Object> postDoctor(@RequestBody @Valid DoctorDTO doctorDTO) {
		
		Doctor doctor = doctorService.addDoctor(doctorDTO);
		
		UriComponents uriComponents = UriComponentsBuilder
				.fromUriString("/api/v1.0/doctors/{id}") 
				.encode() 
				.build(); 

		URI uri = uriComponents.expand(doctor.getId()).toUri();
		
		return ResponseEntity.created(uri).body(doctor);
	}
	
	/**
	 * Get method to receive a Doctor record by the provided ID
	 *
	 * @param id A long representing the doctor's unique identifier
	 * 
	 * @return A response entity containing the corresponding doctor if successful, or
	 * a 400-level error if it is non-existent
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Object> getDoctor(@PathVariable Long id) {
	
		Doctor doctor = doctorService.findDoctorById(id);
		
		return ResponseEntity.ok(doctor);
	}
	
	/**
	 * Get method to receive a paginated sublist of objects containing data transfer objects with Doctor public information
	 *
	 * @param pageable Pagination information, such as size and page number
	 * 
	 * @return A response entity containing the paginated sublist of doctors if successful
	 */
	@GetMapping
	public ResponseEntity<Object> getDoctors(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable) {
		
		Page<DoctorPublicDataDTO> doctors = doctorService.findDoctors(pageable);
		
		return ResponseEntity.ok(doctors);
	}
	
	/**
	 * Put method to update a existing doctor record by provided ID
	 *
	 * @param doctorUpdatedDataDTO Data Transfer Object containing allowed data to be updated in Doctor entity
	 * 
	 * @return A response entity containing the saved doctor if successful, or
	 * a 400-level error if there is a validation error
	 */
	@PutMapping
	public ResponseEntity<Object> putDoctor(@RequestBody @Valid DoctorUpdatedDataDTO doctorUpdatedDataDTO) {
		
		Doctor doctor = doctorService.updateDoctor(doctorUpdatedDataDTO);
		
		return ResponseEntity.ok(doctor);
	}
	
	/**
	 * Delete method to deactivate a existing doctor record by provided ID
	 *
	 * @param id Path variable that represents the doctor's unique identifier
	 * 
	 * @return A response entity containing the deactivated doctor if successful
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteDoctor(@PathVariable Long id) {
		Doctor doctor = doctorService.deactivateDoctor(id);
		
		return ResponseEntity.ok(doctor);
	}
}

package com.mirna.hospitalmanagementapi.application.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mirna.hospitalmanagementapi.domain.dtos.DoctorDTO;
import com.mirna.hospitalmanagementapi.domain.entities.Doctor;
import com.mirna.hospitalmanagementapi.domain.services.DoctorService;

import jakarta.validation.Valid;

/**
 * A Spring REST controller for managing doctors.
 *
 * @author Mirna Gama
 * @version 1.0
 */
@RestController
@RequestMapping("/api/v1.0/doctors")
public class DoctorController {

	@Autowired
	private DoctorService doctorService;
	
	
	@PostMapping
	public ResponseEntity<Object> postDoctor(@RequestBody @Valid DoctorDTO doctorDTO) {
		
		Doctor doctor = doctorService.addDoctor(doctorDTO);
		
		return ResponseEntity.ok(doctor);
		
	}
}

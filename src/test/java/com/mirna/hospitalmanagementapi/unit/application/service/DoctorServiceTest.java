package com.mirna.hospitalmanagementapi.unit.application.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.mirna.hospitalmanagementapi.HospitalManagementApiApplication;
import com.mirna.hospitalmanagementapi.application.services.DoctorServiceImpl;
import com.mirna.hospitalmanagementapi.domain.dtos.AddressDTO;
import com.mirna.hospitalmanagementapi.domain.dtos.DoctorDTO;
import com.mirna.hospitalmanagementapi.domain.entities.Doctor;
import com.mirna.hospitalmanagementapi.domain.enums.Specialty;

import jakarta.validation.ConstraintViolationException;

/**
 * 
 * @author Mirna Gama
 * @version 1.0
 */
@SpringBootTest(classes = HospitalManagementApiApplication.class)
@TestInstance(Lifecycle.PER_CLASS)
@ActiveProfiles("test")
public class DoctorServiceTest {

	@Autowired
	private DoctorServiceImpl doctorService;
	
	/**
	 * Create a valid doctor.
	 */
	@Test
	@DisplayName("Should add valid doctor")
	public void testAddValidDoctor() throws Exception {
		DoctorDTO doctorDTO = new DoctorDTO("test", "test@gmail.com", "123456", "99999999", Specialty.ORTHOPEDICS,
				new AddressDTO("TEST STREET", "NEIGHBORHOOD", "12345678", "CITY", "ST", null, null));
		
		Doctor doctor = doctorService.addDoctor(doctorDTO);
		
		assertNotNull(doctor.getId());
	}
	
	/**
	 * Avoid creating a doctor with invalid parameter. Example: name being blank
	 */
	@Test
	@DisplayName("Should not add doctor with invalid parameter")
	public void testAddInvalidDoctor() throws Exception {
		DoctorDTO doctorDTO = new DoctorDTO("", "test@gmail.com", "123456", "99999999", Specialty.ORTHOPEDICS,
				new AddressDTO("TEST STREET", "NEIGHBORHOOD", "12345678", "CITY", "ST", null, null));
		
		assertThrows(ConstraintViolationException.class, () ->  doctorService.addDoctor(doctorDTO));
	}
}

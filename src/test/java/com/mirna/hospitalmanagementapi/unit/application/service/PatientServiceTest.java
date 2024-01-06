package com.mirna.hospitalmanagementapi.unit.application.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.mirna.hospitalmanagementapi.HospitalManagementApiApplication;
import com.mirna.hospitalmanagementapi.application.services.PatientServiceImpl;
import com.mirna.hospitalmanagementapi.domain.dtos.AddressDTO;
import com.mirna.hospitalmanagementapi.domain.dtos.patient.PatientDTO;
import com.mirna.hospitalmanagementapi.domain.entities.Patient;
import com.mirna.hospitalmanagementapi.domain.repositories.PatientRepository;

import jakarta.validation.ConstraintViolationException;

/**
* 
* @author Mirna Gama
* @version 1.0
*/
@SpringBootTest(classes = HospitalManagementApiApplication.class)
@TestInstance(Lifecycle.PER_CLASS)
@ActiveProfiles("test")
public class PatientServiceTest {

	@Autowired
	private PatientServiceImpl patientService;
	
	@Autowired
	private PatientRepository patientRepository;
	
	private Patient testPatient;
	
	@BeforeAll
	public void init() {
		PatientDTO patientDTO = new PatientDTO("test", "test@gmail.com", "11111111111", "99999999",
				new AddressDTO("TEST STREET", "NEIGHBORHOOD", "12345678", "CITY", "ST", null, null));
		
		testPatient = patientRepository.save(new Patient(patientDTO));
	}
	
	@AfterAll
	public void terminate() {
		patientRepository.deleteAll();
	}
	/**
	 * Create a valid patient.
	 */
	@Test
	@DisplayName("Should add valid patient")
	public void testAddValidPatient() throws Exception {
		PatientDTO patientDTO = new PatientDTO("test1", "test1@gmail.com", "10111111111", "99999999",
				new AddressDTO("TEST STREET", "NEIGHBORHOOD", "12345678", "CITY", "ST", null, null));
		
		Patient patient = patientService.addPatient(patientDTO);
		
		assertNotNull(patient.getId());
	}
	
	/**
	 * Avoid creating a patient with invalid parameter. Example: name being blank
	 */
	@Test
	@DisplayName("Should not add patient with invalid parameter")
	public void testAddInvalidPatient() throws Exception {
		PatientDTO patientDTO = new PatientDTO("", "test2@gmail.com", "10111111112", "99999999",
				new AddressDTO("TEST STREET", "NEIGHBORHOOD", "12345678", "CITY", "ST", null, null));
		
		assertThrows(ConstraintViolationException.class, () ->  patientService.addPatient(patientDTO));
	}
	
	/**
	 * Finds a patient by provided id.
	 */
	@Test
	@DisplayName("Should find patient by id")
	public void testFindPatientById() throws Exception {
		
		Patient patient = patientService.findPatientById(testPatient.getId());

		assertNotNull(patient);
		
	}
}

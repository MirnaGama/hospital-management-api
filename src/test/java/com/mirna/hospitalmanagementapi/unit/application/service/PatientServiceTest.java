package com.mirna.hospitalmanagementapi.unit.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import com.mirna.hospitalmanagementapi.HospitalManagementApiApplication;
import com.mirna.hospitalmanagementapi.application.services.PatientServiceImpl;
import com.mirna.hospitalmanagementapi.domain.dtos.AddressDTO;
import com.mirna.hospitalmanagementapi.domain.dtos.patient.PatientDTO;
import com.mirna.hospitalmanagementapi.domain.dtos.patient.PatientPublicDataDTO;
import com.mirna.hospitalmanagementapi.domain.dtos.patient.PatientUpdatedDataDTO;
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
		PatientDTO patientDTO1 = new PatientDTO("test1", "test1@gmail.com", "11111111111", "99999999",
				new AddressDTO("TEST STREET", "NEIGHBORHOOD", "12345678", "CITY", "ST", null, null));
		
		PatientDTO patientDTO2 = new PatientDTO("test2", "test2@gmail.com", "22222222222", "99999999",
				new AddressDTO("TEST STREET", "NEIGHBORHOOD", "12345678", "CITY", "ST", null, null));
		
		PatientDTO patientDTO3 = new PatientDTO("test3", "test3@gmail.com", "33333333333", "99999999",
				new AddressDTO("TEST STREET", "NEIGHBORHOOD", "12345678", "CITY", "ST", null, null));
		
		testPatient = patientRepository.save(new Patient(patientDTO1));
		patientRepository.save(new Patient(patientDTO2));
		patientRepository.save(new Patient(patientDTO3));
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
		PatientDTO patientDTO = new PatientDTO("test4", "test4@gmail.com", "10111111111", "99999999",
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
		PatientDTO patientDTO = new PatientDTO("", "test5@gmail.com", "10111111112", "99999999",
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
	
	/**
	 * Finds patients stored in the database with pagination
	 */
	@Test
	@DisplayName("Should find patients with pagination")
	public void testFindPatients() throws Exception {
		Pageable pageable = PageRequest.of(0, 3);
		
		Page<PatientPublicDataDTO> patients = patientService.findPatients(pageable);
		
		assertEquals(patients.getSize(), 3);
	}
	
	/**
	 * Updates an existing patient by id
	 */
	@Test
	@DisplayName("Should update a valid patient") 
	public void testUpdatePatient() throws Exception {
		
		PatientUpdatedDataDTO patientUpdatedData = new PatientUpdatedDataDTO(testPatient.getId(), "updated_test", null, null);
		
		Patient patient = patientService.updatePatient(patientUpdatedData);
		
		assertEquals(patientUpdatedData.name(), patient.getName());
	}
	
	/**
	 * Deactivates an existing patient by id
	 */
	@Test
	@DisplayName("Should deactivate a patient")
	public void testDeactivatePatient() throws Exception {

		Long id = testPatient.getId();

		Patient patient = patientService.deactivatePatient(id);

		assertFalse(patient.getActive());
	}
}

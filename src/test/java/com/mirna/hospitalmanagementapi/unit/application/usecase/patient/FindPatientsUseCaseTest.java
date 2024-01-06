package com.mirna.hospitalmanagementapi.unit.application.usecase.patient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
import com.mirna.hospitalmanagementapi.application.usecase.patient.FindPatientsUseCase;
import com.mirna.hospitalmanagementapi.domain.dtos.AddressDTO;
import com.mirna.hospitalmanagementapi.domain.dtos.patient.PatientDTO;
import com.mirna.hospitalmanagementapi.domain.entities.Patient;
import com.mirna.hospitalmanagementapi.domain.repositories.PatientRepository;

/**
 * 
 * @author Mirna Gama
 * @version 1.0
 */
@SpringBootTest(classes = HospitalManagementApiApplication.class)
@TestInstance(Lifecycle.PER_CLASS)
@ActiveProfiles("test")
public class FindPatientsUseCaseTest {

	@Autowired
	private FindPatientsUseCase findPatients;
	
	@Autowired
	private PatientRepository patientRepository;
	
	@BeforeAll
	public void init() {
		PatientDTO patientDTO1 = new PatientDTO("test1", "test1@gmail.com", "11111111111", "99999999",
				new AddressDTO("TEST STREET", "NEIGHBORHOOD", "12345678", "CITY", "ST", null, null));
		
		PatientDTO patientDTO2 = new PatientDTO("test2", "test2@gmail.com", "22222222222", "99999999",
				new AddressDTO("TEST STREET", "NEIGHBORHOOD", "12345678", "CITY", "ST", null, null));
		
		PatientDTO patientDTO3 = new PatientDTO("test3", "test3@gmail.com", "33333333333", "99999999",
				new AddressDTO("TEST STREET", "NEIGHBORHOOD", "12345678", "CITY", "ST", null, null));
		
		patientRepository.save(new Patient(patientDTO1));
		patientRepository.save(new Patient(patientDTO2));
		patientRepository.save(new Patient(patientDTO3));
	}
	
	@AfterAll
	public void terminate() {
		patientRepository.deleteAll();
	}
	
	/**
	 * Should execute findPatientsByActiveTrue method successfully
	 * 
	 */
	@Test
	@DisplayName("Should execute findPatientsByActiveTrue method with pagination")
	public void testFindPatientById() throws Exception {
				
		Pageable pageable = PageRequest.of(0, 3);
		
		Page<Patient> patients = findPatients.execute(pageable);

		assertEquals(patients.getSize(), 3);
	}
}

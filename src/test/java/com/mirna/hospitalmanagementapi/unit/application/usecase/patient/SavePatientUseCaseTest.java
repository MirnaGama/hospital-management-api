package com.mirna.hospitalmanagementapi.unit.application.usecase.patient;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.mirna.hospitalmanagementapi.HospitalManagementApiApplication;
import com.mirna.hospitalmanagementapi.application.usecase.patient.SavePatientUseCase;
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
public class SavePatientUseCaseTest {

	@Autowired
	private SavePatientUseCase savePatient;
	
	@Autowired
	private PatientRepository patientRepository;
	
	@AfterAll
	public void terminate() {
		patientRepository.deleteAll();
	}
	
	/**
	 * Should execute save method successfully
	 * 
	 */
	@Test
	@DisplayName("Should execute save method")
	public void testSavePatient() throws Exception {
		PatientDTO patientDTO = new PatientDTO("test", "test@gmail.com", "11111111111", "99999999",
				new AddressDTO("TEST STREET", "NEIGHBORHOOD", "12345678", "CITY", "ST", null, null));
				
		Patient patient = new Patient(patientDTO);
		
		patient = savePatient.execute(patient);

		assertNotNull(patient.getId());
	}
}

package com.mirna.hospitalmanagementapi.unit.application.usecase.consultation;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.time.Month;

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
import com.mirna.hospitalmanagementapi.application.usecase.consultation.SaveConsultationUseCase;
import com.mirna.hospitalmanagementapi.domain.dtos.AddressDTO;
import com.mirna.hospitalmanagementapi.domain.dtos.doctor.DoctorDTO;
import com.mirna.hospitalmanagementapi.domain.dtos.patient.PatientDTO;
import com.mirna.hospitalmanagementapi.domain.entities.Consultation;
import com.mirna.hospitalmanagementapi.domain.entities.Doctor;
import com.mirna.hospitalmanagementapi.domain.entities.Patient;
import com.mirna.hospitalmanagementapi.domain.enums.Specialty;
import com.mirna.hospitalmanagementapi.domain.repositories.ConsultationRepository;
import com.mirna.hospitalmanagementapi.domain.repositories.DoctorRepository;
import com.mirna.hospitalmanagementapi.domain.repositories.PatientRepository;

/**
 * 
 * @author Mirna Gama
 * @version 1.0
 */
@SpringBootTest(classes = HospitalManagementApiApplication.class)
@TestInstance(Lifecycle.PER_CLASS)
@ActiveProfiles("test")
public class SaveConsultationUseCaseTest {

	@Autowired
	private SaveConsultationUseCase saveConsultation;
	
	private Doctor testDoctor;
	private Patient testPatient;
	
	@Autowired
	private ConsultationRepository consultationRepository;
	
	@Autowired
	private PatientRepository patientRepository;
	
	@Autowired
	private DoctorRepository doctorRepository;
	
	@BeforeAll
	public void init() {
		PatientDTO patientDTO = new PatientDTO("testPatient", "testPatient@gmail.com", "11111111111", "99999999",
				new AddressDTO("TEST STREET", "NEIGHBORHOOD", "12345678", "CITY", "ST", null, null));
		testPatient = this.patientRepository.save(new Patient(patientDTO));
		
		DoctorDTO doctorDTO = new DoctorDTO("testDoctor", "testDoctor@gmail.com", "123456", "99999999", Specialty.ORTHOPEDICS,
				new AddressDTO("TEST STREET", "NEIGHBORHOOD", "12345678", "CITY", "ST", null, null));
		testDoctor = this.doctorRepository.save(new Doctor(doctorDTO));
	}
	
	@AfterAll
	private void terminate() {
		this.consultationRepository.deleteAll();
		this.doctorRepository.deleteAll();
		this.patientRepository.deleteAll();
	}
	
	/**
	 * Should execute save method successfully
	 * 
	 */
	@Test
	@DisplayName("Should execute save method")
	public void testSaveConsultation() {
		
		Consultation consultation = new Consultation(testPatient, 
				testDoctor, LocalDateTime.now());
		
		consultation = saveConsultation.execute(consultation);
		
		assertNotNull(consultation.getId());
	}

}

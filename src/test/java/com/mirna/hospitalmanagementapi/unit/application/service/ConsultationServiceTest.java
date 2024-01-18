package com.mirna.hospitalmanagementapi.unit.application.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.time.Month;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.mirna.hospitalmanagementapi.HospitalManagementApiApplication;
import com.mirna.hospitalmanagementapi.application.services.ConsultationServiceImpl;
import com.mirna.hospitalmanagementapi.domain.dtos.AddressDTO;
import com.mirna.hospitalmanagementapi.domain.dtos.consultation.ConsultationCanceledDTO;
import com.mirna.hospitalmanagementapi.domain.dtos.consultation.ConsultationDTO;
import com.mirna.hospitalmanagementapi.domain.dtos.doctor.DoctorDTO;
import com.mirna.hospitalmanagementapi.domain.dtos.patient.PatientDTO;
import com.mirna.hospitalmanagementapi.domain.entities.Consultation;
import com.mirna.hospitalmanagementapi.domain.entities.Doctor;
import com.mirna.hospitalmanagementapi.domain.entities.Patient;
import com.mirna.hospitalmanagementapi.domain.enums.ReasonCancellation;
import com.mirna.hospitalmanagementapi.domain.enums.Specialty;
import com.mirna.hospitalmanagementapi.domain.exceptions.ConsultationValidationException;
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
@TestMethodOrder(OrderAnnotation.class)
@ActiveProfiles("test")
public class ConsultationServiceTest {

	@Autowired
	private ConsultationServiceImpl consultationService;
	
	private Long testDoctorId;

	private Long testPatientId1;
	private Long testPatientId2;
	
	private Long testInactiveDoctorId;
	private Long testInactivePatientId;
	
	private Long testConsultationId;
	
	private LocalDateTime testConsultationDate1;
	private LocalDateTime testConsultationDate2;
	
	@Autowired
	private ConsultationRepository consultationRepository;
	
	@Autowired
	private PatientRepository patientRepository;
	
	@Autowired
	private DoctorRepository doctorRepository;
	
	@BeforeAll
	public void init() {
		testConsultationDate1 = LocalDateTime.of(2024, Month.JULY, 29, 10, 34);
		testConsultationDate2 = LocalDateTime.of(2024, Month.DECEMBER, 10, 16, 30);
		
		PatientDTO patientDTO1 = new PatientDTO("testPatient1", "testPatient1@gmail.com", "11111111111", "99999999",
				new AddressDTO("TEST STREET", "NEIGHBORHOOD", "12345678", "CITY", "ST", null, null));
		testPatientId1 = this.patientRepository.save(new Patient(patientDTO1)).getId();
		
		PatientDTO patientDTO2 = new PatientDTO("testPatient2", "testPatient2@gmail.com", "22222222222", "99999999",
				new AddressDTO("TEST STREET", "NEIGHBORHOOD", "12345678", "CITY", "ST", null, null));
		testPatientId2 = this.patientRepository.save(new Patient(patientDTO2)).getId();
		
		DoctorDTO doctorDTO1 = new DoctorDTO("testDoctor1", "testDoctor1@gmail.com", "123456", "99999999", Specialty.ORTHOPEDICS,
				new AddressDTO("TEST STREET", "NEIGHBORHOOD", "12345678", "CITY", "ST", null, null));
		testDoctorId = this.doctorRepository.save(new Doctor(doctorDTO1)).getId();
		
		DoctorDTO doctorDTO2 = new DoctorDTO("testDoctor2", "testDoctor2@gmail.com", "789101", "99999999", Specialty.CARDIOLOGY,
				new AddressDTO("TEST STREET", "NEIGHBORHOOD", "12345678", "CITY", "ST", null, null));
		this.doctorRepository.save(new Doctor(doctorDTO2)).getId();
		
		// inative test patient only
		PatientDTO patientDTO3 = new PatientDTO("testPatient3", "testPatient3@gmail.com", "33333333333", "99999999",
				new AddressDTO("TEST STREET", "NEIGHBORHOOD", "12345678", "CITY", "ST", null, null));
		Patient inativePatient = new Patient(patientDTO3);
		inativePatient.setActive(false);
		testInactivePatientId = this.patientRepository.save(inativePatient).getId();
		
		// inactive test doctor only
		DoctorDTO doctorDTO3 = new DoctorDTO("testDoctor3", "testDoctor3@gmail.com", "999999", "99999999", Specialty.ORTHOPEDICS,
				new AddressDTO("TEST STREET", "NEIGHBORHOOD", "12345678", "CITY", "ST", null, null));
		Doctor inactiveDoctor = new Doctor(doctorDTO3); 
		inactiveDoctor.setActive(false);
		testInactiveDoctorId = this.doctorRepository.save(inactiveDoctor).getId();
		
	}
	
	@AfterAll
	private void terminate() {
		this.consultationRepository.deleteAll();
		this.doctorRepository.deleteAll();
		this.patientRepository.deleteAll();
	}
	
	/**
	 * Should add a valid consultation by doctor id
	 * 
	 */
	@Test
	@Order(1)
	@DisplayName("Should add a valid consultation by doctor id")
	public void testAddValidConsultationByDoctorId() throws Exception {
		
		ConsultationDTO consultationDTO = new ConsultationDTO(testDoctorId, testPatientId1, testConsultationDate1, null);
		
		Consultation consultation = consultationService.addConsultation(consultationDTO);
		
		assertNotNull(consultation.getId());
		
	}
	
	/**
	 * Should add a valid consultation by specialty
	 * 
	 */
	@Test
	@Order(2)
	@DisplayName("Should add a valid consultation by specialty")
	public void testAddValidConsultationBySpecialty() throws Exception {
		
		ConsultationDTO consultationDTO = new ConsultationDTO(null, testPatientId1, testConsultationDate2, Specialty.CARDIOLOGY);
		
		Consultation consultation = consultationService.addConsultation(consultationDTO);
		
		testConsultationId = consultation.getId();
		
		assertNotNull(consultation.getId());
		
	}

	/**
	 * Avoid adding a invalid consultation if patient is already scheduled on the same date
	 * 
	 */
	@Test
	@Order(3)
	@DisplayName("Should not add a invalid consultation if patient is already scheduled on the same date")
	public void testAddInvalidConsultationWithPatientAlreadyScheduledOnTheSameDate() throws Exception {
	
		ConsultationDTO consultationDTO = new ConsultationDTO(null, testPatientId1, testConsultationDate2, Specialty.CARDIOLOGY);
		
		assertThrows(ConsultationValidationException.class, () -> consultationService.addConsultation(consultationDTO));
		
	}

	/**
	 * Avoid adding a invalid consultation if doctor is already scheduled on the same date
	 * 
	 */
	@Test
	@Order(4)
	@DisplayName("Should not add a invalid consultation if doctor is already scheduled on the same date")
	public void testAddInvalidConsultationByDoctorAlreadyScheduledOnTheSameDate() throws Exception {
	
		ConsultationDTO consultationDTO = new ConsultationDTO(testDoctorId, testPatientId2, testConsultationDate1, null);
		
		assertThrows(ConsultationValidationException.class, () -> consultationService.addConsultation(consultationDTO));
		
	}
	
	/**
	 * Avoid adding a invalid consultation if there is no specialist doctor free on the consultation date
	 * 
	 */
	@Test
	@Order(5)
	@DisplayName("Should not add a invalid consultation if there is no specialist doctor free")
	public void testAddInvalidConsultationBySpecialtyWithNoDoctorFree() throws Exception {
		
		ConsultationDTO consultationDTO = new ConsultationDTO(null, testPatientId2, testConsultationDate2, Specialty.CARDIOLOGY);
		
		assertThrows(ConsultationValidationException.class, () -> consultationService.addConsultation(consultationDTO));
	}
	
	/**
	 * Avoid adding a invalid consultation if the patient is inactive
	 * 
	 */
	@Test
	@Order(6)
	@DisplayName("Should not add a invalid consultation if the doctor is inactive")
	public void testAddInvalidConsultationWithInactivePatientId() throws Exception {
		
		ConsultationDTO consultationDTO = new ConsultationDTO(null, testInactivePatientId, LocalDateTime.now(), Specialty.CARDIOLOGY);
		
		assertThrows(ConsultationValidationException.class, () -> consultationService.addConsultation(consultationDTO));
	}
	
	/**
	 * Avoid adding a invalid consultation if the doctor is inactive
	 * 
	 */
	@Test
	@Order(7)
	@DisplayName("Should not add a invalid consultation if the doctor is inactive")
	public void testAddInvalidConsultationByInactiveDoctorId() throws Exception {
		
		ConsultationDTO consultationDTO = new ConsultationDTO(testInactiveDoctorId, testPatientId1, LocalDateTime.now(), null);
		
		assertThrows(ConsultationValidationException.class, () -> consultationService.addConsultation(consultationDTO));
	}
	
	/**
	 * Cancels a valid consultation by consultation id
	 * 
	 */
	@Test
	@Order(8)
	@DisplayName("Should cancel a consultation by consultation id")
	public void testCancelValidConsultation() throws Exception {
		
		ConsultationCanceledDTO consultationCanceledDTO = new ConsultationCanceledDTO(testConsultationId, ReasonCancellation.PATIENT_GAVE_UP);
		
		Consultation consultation = consultationService.cancelConsultation(consultationCanceledDTO);
		
		assertTrue(consultation.isCanceled());
	}
	
	
}

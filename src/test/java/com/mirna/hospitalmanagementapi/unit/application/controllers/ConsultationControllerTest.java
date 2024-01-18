package com.mirna.hospitalmanagementapi.unit.application.controllers;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import com.mirna.hospitalmanagementapi.domain.repositories.ConsultationRepository;
import com.mirna.hospitalmanagementapi.domain.repositories.DoctorRepository;
import com.mirna.hospitalmanagementapi.domain.repositories.PatientRepository;

/**
 * 
 * @author Mirna Gama
 * @version 1.0
 */
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
@ActiveProfiles("test")
public class ConsultationControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper mapper;
	
	private Long testDoctorId;
	private Long testPatientId;
	
	private Long testConsultationId;
	
	@Autowired
	private ConsultationRepository consultationRepository;
	
	@Autowired
	private PatientRepository patientRepository;
	
	@Autowired
	private DoctorRepository doctorRepository;
	
	@BeforeAll
	public void init() {
		
		PatientDTO patientDTO1 = new PatientDTO("testPatient1", "testPatient1@gmail.com", "11111111111", "99999999",
				new AddressDTO("TEST STREET", "NEIGHBORHOOD", "12345678", "CITY", "ST", null, null));
		Patient testPatient = this.patientRepository.save(new Patient(patientDTO1));
		testPatientId = testPatient.getId();
		
		DoctorDTO doctorDTO1 = new DoctorDTO("testDoctor1", "testDoctor1@gmail.com", "123456", "99999999", Specialty.ORTHOPEDICS,
				new AddressDTO("TEST STREET", "NEIGHBORHOOD", "12345678", "CITY", "ST", null, null));
		Doctor testDoctor = this.doctorRepository.save(new Doctor(doctorDTO1));
		testDoctorId = testDoctor.getId();
	
		Consultation consultation = new Consultation(testPatient, testDoctor, LocalDateTime.of(2024, Month.DECEMBER, 14, 15, 34));
		
		testConsultationId = this.consultationRepository.save(consultation).getId();
	}
	
	@AfterAll
	private void terminate() {
		this.consultationRepository.deleteAll();
		this.doctorRepository.deleteAll();
		this.patientRepository.deleteAll();
	}
	
	/**
	 * Post a valid consultation by doctor id
	 */
	@Test
	@Order(1)
	@DisplayName("Should post valid consultation by doctor id and return http status ok")
	public void testPostValidConsultationByDoctorId() throws Exception {

		ConsultationDTO consultationDTO = new ConsultationDTO(testDoctorId, testPatientId, 
				LocalDateTime.of(2024, Month.JULY, 9, 10, 34), null);

		String consultationDTOContent = mapper.writeValueAsString(consultationDTO);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1.0/consultations").contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("UTF-8").content(consultationDTOContent))
				.andExpect(MockMvcResultMatchers.status().isCreated()).andDo(MockMvcResultHandlers.print());
	}
	
	/**
	 * Post a valid consultation by specialty
	 */
	@Test
	@Order(2)
	@DisplayName("Should post valid consultation by specialty and return http status ok")
	public void testPostValidConsultationBySpecialty() throws Exception {

		ConsultationDTO consultationDTO = new ConsultationDTO(null, testPatientId, 
				LocalDateTime.of(2024, Month.JULY, 10, 10, 34), Specialty.ORTHOPEDICS);

		String consultationDTOContent = mapper.writeValueAsString(consultationDTO);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1.0/consultations").contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("UTF-8").content(consultationDTOContent))
				.andExpect(MockMvcResultMatchers.status().isCreated()).andDo(MockMvcResultHandlers.print());
	}
	
	/**
	 * Avoid posting a invalid consultation (example: consultation date outside of business hours)
	 */
	@Test
	@Order(3)
	@DisplayName("Should not post invalid consultation with consultation date outside of business hours and return http status bad request")
	public void testPostInvalidConsultationWithConsultationDateOutsideBusinessHours() throws Exception {

		ConsultationDTO consultationDTO = new ConsultationDTO(null, testPatientId, 
				LocalDateTime.of(2024, Month.JULY, 02, 03, 30), Specialty.ORTHOPEDICS);

		String consultationDTOContent = mapper.writeValueAsString(consultationDTO);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1.0/consultations").contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("UTF-8").content(consultationDTOContent))
				.andExpect(MockMvcResultMatchers.status().isBadRequest()).andDo(MockMvcResultHandlers.print());
	}
	
	/**
	 * Delete a valid consultation
	 */
	@Test
	@Order(4)
	@DisplayName("Should delete valid consultation")
	public void testDeleteValidConsultation() throws Exception {

		ConsultationCanceledDTO consultationCanceledDTO = new ConsultationCanceledDTO(testConsultationId, 
				ReasonCancellation.PATIENT_GAVE_UP);

		String consultationCanceledDTOContent = mapper.writeValueAsString(consultationCanceledDTO);

		mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1.0/consultations").contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("UTF-8").content(consultationCanceledDTOContent))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
	}
	
}

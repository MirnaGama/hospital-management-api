package com.mirna.hospitalmanagementapi.unit.application.controllers;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
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
import com.mirna.hospitalmanagementapi.domain.dtos.patient.PatientDTO;
import com.mirna.hospitalmanagementapi.domain.entities.Patient;
import com.mirna.hospitalmanagementapi.domain.repositories.PatientRepository;

/**
 * 
 * @author Mirna Gama
 * @version 1.0
 */
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
public class PatientControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper mapper;
	
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
	 * Post a valid patient.
	 */
	@Test
	@DisplayName("Should post valid patient and return http status created")
	public void testPostValidPatient() throws Exception {
	
		PatientDTO patientDTO = new PatientDTO("test1", "test1@gmail.com", "10111111111", "99999999",
				new AddressDTO("TEST STREET", "NEIGHBORHOOD", "12345678", "CITY", "ST", null, null));
	
		String patientDTOContent = mapper.writeValueAsString(patientDTO);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1.0/patients").contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("UTF-8").content(patientDTOContent))
				.andExpect(MockMvcResultMatchers.status().isCreated()).andDo(MockMvcResultHandlers.print());
	}
	
	/**
	 * Avoid posting a invalid patient.
	 */
	@Test
	@DisplayName("Should not post invalid patient and return http status bad request")
	public void testPostInvalidDoctor() throws Exception {

		PatientDTO patientDTO = new PatientDTO("", "test2@gmail.com", "10111111112", "99999999",
				new AddressDTO("TEST STREET", "NEIGHBORHOOD", "12345678", "CITY", "ST", null, null));

		String patientDTOContent = mapper.writeValueAsString(patientDTO);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1.0/patients").contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("UTF-8").content(patientDTOContent))
				.andExpect(MockMvcResultMatchers.status().isBadRequest()).andDo(MockMvcResultHandlers.print());
	}
	
	/**
	 * Get patient by id
	 */
	@Test
	@DisplayName("Should get patient by id and return http status OK")
	public void testGetPatient() throws Exception {

		Long id = testPatient.getId();
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1.0/patients/{id}", id).contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("UTF-8"))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
	}
}

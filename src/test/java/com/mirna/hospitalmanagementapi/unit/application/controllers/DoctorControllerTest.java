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
import com.mirna.hospitalmanagementapi.domain.dtos.doctor.DoctorDTO;
import com.mirna.hospitalmanagementapi.domain.dtos.doctor.DoctorUpdatedDataDTO;
import com.mirna.hospitalmanagementapi.domain.entities.Doctor;
import com.mirna.hospitalmanagementapi.domain.enums.Specialty;
import com.mirna.hospitalmanagementapi.domain.repositories.DoctorRepository;

/**
 * 
 * @author Mirna Gama
 * @version 1.0
 */
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
public class DoctorControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper mapper;

	@Autowired
	private DoctorRepository doctorRepository;
	
	private Doctor testDoctor;
	
	@BeforeAll
	public void init() {
		DoctorDTO doctorDTO1 = new DoctorDTO("test1", "test1@gmail.com", "123456", "99999999", Specialty.ORTHOPEDICS,
				new AddressDTO("TEST STREET", "NEIGHBORHOOD", "12345678", "CITY", "ST", null, null));
				
		DoctorDTO doctorDTO2 = new DoctorDTO("test2", "test2@gmail.com", "789101", "99999999", Specialty.ORTHOPEDICS,
				new AddressDTO("TEST STREET", "NEIGHBORHOOD", "12345678", "CITY", "ST", null, null));
		
		DoctorDTO doctorDTO3 = new DoctorDTO("test3", "test3@gmail.com", "112131", "99999999", Specialty.ORTHOPEDICS,
				new AddressDTO("TEST STREET", "NEIGHBORHOOD", "12345678", "CITY", "ST", null, null));
	
		testDoctor = doctorRepository.save(new Doctor(doctorDTO1));
		doctorRepository.save(new Doctor(doctorDTO2));
		doctorRepository.save(new Doctor(doctorDTO3));
	}
	
	@AfterAll
	public void terminate() {
		doctorRepository.deleteAll();
	}
	
	/**
	 * Post a valid doctor.
	 */
	@Test
	@DisplayName("Should post valid doctor and return http status created")
	public void testPostValidDoctor() throws Exception {

		DoctorDTO doctorDTO = new DoctorDTO("test", "test@gmail.com", "101010", "99999999", Specialty.ORTHOPEDICS,
				new AddressDTO("TEST STREET", "NEIGHBORHOOD", "12345678", "CITY", "ST", null, null));

		String doctorDTOContent = mapper.writeValueAsString(doctorDTO);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1.0/doctors").contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("UTF-8").content(doctorDTOContent))
				.andExpect(MockMvcResultMatchers.status().isCreated()).andDo(MockMvcResultHandlers.print());
	}
	
	/**
	 * Avoid posting a invalid doctor.
	 */
	@Test
	@DisplayName("Should not post invalid doctor and return http status bad request")
	public void testPostInvalidDoctor() throws Exception {

		DoctorDTO doctorDTO = new DoctorDTO("", "test@gmail.com", "111111", "99999999", Specialty.ORTHOPEDICS,
				new AddressDTO("TEST STREET", "NEIGHBORHOOD", "12345678", "CITY", "ST", null, null));

		String doctorDTOContent = mapper.writeValueAsString(doctorDTO);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1.0/doctors").contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("UTF-8").content(doctorDTOContent))
				.andExpect(MockMvcResultMatchers.status().isBadRequest()).andDo(MockMvcResultHandlers.print());
	}
	
	/**
	 * Get doctor by id
	 */
	@Test
	@DisplayName("Should get doctor by id and return http status OK")
	public void testGetDoctor() throws Exception {

		Long id = testDoctor.getId();
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1.0/doctors/{id}", id).contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("UTF-8"))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
	}
	
	/**
	 * Get doctors with pagination
	 */
	@Test
	@DisplayName("Should get doctors with pagination and return http status OK")
	public void testGetDoctors() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1.0/doctors?size=3").contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("UTF-8"))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
	}
	
	/**
	 * Put a valid doctor
	 */
	@Test
	@DisplayName("Should put a valid doctor and return http status ok")
	public void testPutValidDoctor() throws Exception {

		DoctorUpdatedDataDTO doctorUpdatedDataDTO = new DoctorUpdatedDataDTO(testDoctor.getId(), "updated_test", null, null);

		String doctorUpdatedDataDTOContent = mapper.writeValueAsString(doctorUpdatedDataDTO);

		mockMvc.perform(MockMvcRequestBuilders.put("/api/v1.0/doctors").contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("UTF-8").content(doctorUpdatedDataDTOContent))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
	}
	
	/**
	 * Avoid putting a invalid doctor.
	 */
	@Test
	@DisplayName("Should not put invalid doctor and return http status bad request")
	public void testPutInvalidDoctor() throws Exception {

		DoctorUpdatedDataDTO doctorUpdatedDataDTO = new DoctorUpdatedDataDTO(null, "updated_test", null, null);

		String doctorUpdatedDataDTOContent = mapper.writeValueAsString(doctorUpdatedDataDTO);

		mockMvc.perform(MockMvcRequestBuilders.put("/api/v1.0/doctors").contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("UTF-8").content(doctorUpdatedDataDTOContent))
				.andExpect(MockMvcResultMatchers.status().isBadRequest()).andDo(MockMvcResultHandlers.print());
	}
	
	/**
	 * Delete a doctor
	 */
	@Test
	@DisplayName("Should delete doctor and return http status ok")
	public void testDeleteDoctor() throws Exception {

		Long id = testDoctor.getId();
		                    
		mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1.0/doctors/{id}", id).contentType(MediaType.APPLICATION_JSON)
		    				.characterEncoding("UTF-8"))
		    				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
	}
}

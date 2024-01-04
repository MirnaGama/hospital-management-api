package com.mirna.hospitalmanagementapi.unit.application.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mirna.hospitalmanagementapi.domain.dtos.AddressDTO;
import com.mirna.hospitalmanagementapi.domain.dtos.DoctorDTO;
import com.mirna.hospitalmanagementapi.domain.enums.Specialty;

/**
 * 
 * @author Mirna Gama
 * @version 1.0
 */
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DoctorControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper mapper;

	/**
	 * Post a valid doctor.
	 */
	@Test
	@DisplayName("Should post valid doctor and return http status ok")
	public void testPostValidDoctor() throws Exception {

		DoctorDTO doctorDTO = new DoctorDTO("test", "test@gmail.com", "123456", "99999999", Specialty.ORTHOPEDICS,
				new AddressDTO("TEST STREET", "NEIGHBORHOOD", "12345678", "CITY", "ST", null, null));

		String doctorDTOContent = mapper.writeValueAsString(doctorDTO);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1.0/doctors").contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("UTF-8").content(doctorDTOContent))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
	}
	
	/**
	 * Avoid posting a invalid doctor.
	 */
	@Test
	@DisplayName("Should not post invalid doctor and return http status bad request")
	public void testPostInvalidDoctor() throws Exception {

		DoctorDTO doctorDTO = new DoctorDTO("", "test@gmail.com", "123456", "99999999", Specialty.ORTHOPEDICS,
				new AddressDTO("TEST STREET", "NEIGHBORHOOD", "12345678", "CITY", "ST", null, null));

		String doctorDTOContent = mapper.writeValueAsString(doctorDTO);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1.0/doctors").contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("UTF-8").content(doctorDTOContent))
				.andExpect(MockMvcResultMatchers.status().isBadRequest()).andDo(MockMvcResultHandlers.print());
	}
}

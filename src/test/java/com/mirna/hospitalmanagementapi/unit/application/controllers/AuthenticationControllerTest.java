package com.mirna.hospitalmanagementapi.unit.application.controllers;

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
import com.mirna.hospitalmanagementapi.domain.dtos.auth.UserDTO;

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
public class AuthenticationControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper mapper;

	
	/**
	 * Post the user registration
	 */
	@Test
	@Order(1)
	@DisplayName("Should post the user registration and return http status ok")
	public void testPostRegister() throws Exception {

		UserDTO userDTO = new UserDTO("test", "password");
		
		String userDTOContent = mapper.writeValueAsString(userDTO);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/register").contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("UTF-8").content(userDTOContent))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
	}

	/**
	 * Post the user login
	 */
	@Test
	@Order(2)
	@DisplayName("Should post the user login and return http status ok")
	public void testPostLogin() throws Exception {

		UserDTO userDTO = new UserDTO("test", "password");
		
		String userDTOContent = mapper.writeValueAsString(userDTO);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/login").contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("UTF-8").content(userDTOContent))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
	}
	
}

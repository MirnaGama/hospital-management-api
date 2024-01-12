package com.mirna.hospitalmanagementapi.unit.application.service.auth;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.ActiveProfiles;

import com.mirna.hospitalmanagementapi.application.services.auth.AuthServiceImpl;
import com.mirna.hospitalmanagementapi.domain.dtos.auth.UserDTO;
import com.mirna.hospitalmanagementapi.domain.entities.auth.User;

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
public class AuthServiceTest {

	@Autowired
	private AuthServiceImpl authService;
	
	/**
	 * Performs user registration
	 */
	@Test
	@Order(1)
	@DisplayName("Should perform user registration")
	public void testRegister() throws Exception {
		UserDTO userDTO = new UserDTO("testUser2", "password");
		
		User user = authService.register(userDTO);
		
		assertNotNull(user.getId());
	}
	
	/**
	 * Performs user login
	 */
	@Test
	@Order(2)
	@DisplayName("Should perform user login")
	public void testLogin() throws Exception {
		UserDTO userDTO = new UserDTO("testUser2", "password");
		
		Authentication auth = authService.login(userDTO);
		
		assertNotNull(auth.getPrincipal());
	}
}

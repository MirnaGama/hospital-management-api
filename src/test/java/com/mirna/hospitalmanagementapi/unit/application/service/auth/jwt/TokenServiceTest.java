package com.mirna.hospitalmanagementapi.unit.application.service.auth.jwt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.mirna.hospitalmanagementapi.application.services.auth.jwt.TokenServiceImpl;
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
public class TokenServiceTest {

	@Autowired
	private TokenServiceImpl tokenService;
	
	private String testToken;
	
	/**
	 * Generates the authorization token
	 */
	@Test
	@Order(1)
	@DisplayName("Should generate an authorization token")
	public void testGenerateToken() throws Exception {
		UserDTO userDTO = new UserDTO("test", "password");
		
		testToken = tokenService.generateToken(new User(userDTO));
		
		assertNotNull(testToken);
	}
	
	/**
	 * Gets the jwt subject 
	 */
	@Test
	@Order(2)
	@DisplayName("Should get the authorization token subject")
	public void testGetTokenSubject() throws Exception {
		String subject = tokenService.getTokenSubject(testToken);
		
		assertEquals("test", subject);
	}
}

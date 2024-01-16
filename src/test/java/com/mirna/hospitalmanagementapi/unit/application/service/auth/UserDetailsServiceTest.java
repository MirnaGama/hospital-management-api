package com.mirna.hospitalmanagementapi.unit.application.service.auth;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.mirna.hospitalmanagementapi.application.services.auth.UserDetailsServiceImpl;
import com.mirna.hospitalmanagementapi.domain.dtos.auth.UserDTO;
import com.mirna.hospitalmanagementapi.domain.entities.auth.User;
import com.mirna.hospitalmanagementapi.domain.repositories.auth.UserRepository;

/**
 * 
 * @author Mirna Gama
 * @version 1.0
 */
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
public class UserDetailsServiceTest {

	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	@Autowired
	private UserRepository userRepository;

	@BeforeAll
	public void init() {
		User user = new User(new UserDTO("test000", "p3ssw3rd"));
		userRepository.save(user);
	}
	
	@AfterAll
	public void terminate() {
		userRepository.deleteAll();
	}
	
	/**
	 * Should load user by username successfully
	 * 
	 */
	@Test
	@DisplayName("Should load user by username")
	public void testFindUserByLogin() throws Exception {
		User user = (User) userDetailsService.loadUserByUsername("test000");
		
		assertNotNull(user);
	}
}

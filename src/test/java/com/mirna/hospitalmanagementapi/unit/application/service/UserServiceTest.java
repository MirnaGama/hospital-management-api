package com.mirna.hospitalmanagementapi.unit.application.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.mirna.hospitalmanagementapi.HospitalManagementApiApplication;
import com.mirna.hospitalmanagementapi.application.services.UserServiceImpl;
import com.mirna.hospitalmanagementapi.domain.dtos.auth.UserDTO;
import com.mirna.hospitalmanagementapi.domain.entities.auth.User;
import com.mirna.hospitalmanagementapi.domain.repositories.auth.UserRepository;

import jakarta.validation.ConstraintViolationException;

/**
 * 
 * @author Mirna Gama
 * @version 1.0
 */
@SpringBootTest(classes = HospitalManagementApiApplication.class)
@TestInstance(Lifecycle.PER_CLASS)
@ActiveProfiles("test")
public class UserServiceTest {

	@Autowired
	private UserServiceImpl userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@AfterAll
	public void terminate() {
		userRepository.deleteAll();
	}
	
	/**
	 * Create a valid doctor.
	 */
	@Test
	@DisplayName("Should add valid user")
	public void testAddValidUser() {
		UserDTO userDTO = new UserDTO("testUser", "password");
		
		User user = userService.addUser(userDTO);
		
		assertNotNull(user.getId());
	}
	
	/**
	 * Avoid creating a user with invalid parameter. Example: login being blank
	 */
	@Test
	@DisplayName("Should not add user with invalid parameter")
	public void testAddInvalidUser() {
		UserDTO userDTO = new UserDTO("", "password");
		
		assertThrows(ConstraintViolationException.class, () ->  userService.addUser(userDTO));
	}

}

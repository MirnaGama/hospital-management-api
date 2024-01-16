package com.mirna.hospitalmanagementapi.unit.application.usecase.user;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.mirna.hospitalmanagementapi.HospitalManagementApiApplication;
import com.mirna.hospitalmanagementapi.application.usecase.user.FindUserByLoginUseCase;
import com.mirna.hospitalmanagementapi.domain.dtos.auth.UserDTO;
import com.mirna.hospitalmanagementapi.domain.entities.auth.User;
import com.mirna.hospitalmanagementapi.domain.repositories.auth.UserRepository;

/**
 * 
 * @author Mirna Gama
 * @version 1.0
 */
@SpringBootTest(classes = HospitalManagementApiApplication.class)
@TestInstance(Lifecycle.PER_CLASS)
@ActiveProfiles("test")
public class FindUserByLoginUseCaseTest {

	@Autowired
	private FindUserByLoginUseCase findUserByLogin;
	
	@Autowired
	private UserRepository userRepository;
	
	@BeforeAll
	public void init() {
		User user = new User(new UserDTO("testUser", "password"));
		userRepository.save(user);
	}
	
	@AfterAll
	public void terminate() {
		userRepository.deleteAll();
	}
	
	/**
	 * Should execute findUserByLogin method successfully
	 * 
	 */
	@Test
	@DisplayName("Should execute findUserByLogin method")
	public void testFindUserByLogin() throws Exception {
		User user = (User) findUserByLogin.execute("testUser");
		
		assertNotNull(user);
	}
	
	
}

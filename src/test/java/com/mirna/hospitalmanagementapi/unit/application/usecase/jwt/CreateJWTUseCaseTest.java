package com.mirna.hospitalmanagementapi.unit.application.usecase.jwt;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.mirna.hospitalmanagementapi.HospitalManagementApiApplication;
import com.mirna.hospitalmanagementapi.application.usecase.auth.jwt.CreateJWTUseCase;
import com.mirna.hospitalmanagementapi.domain.dtos.auth.UserDTO;
import com.mirna.hospitalmanagementapi.domain.entities.auth.User;

/**
 * 
 * @author Mirna Gama
 * @version 1.0
 */
@SpringBootTest(classes = HospitalManagementApiApplication.class)
@TestInstance(Lifecycle.PER_CLASS)
@ActiveProfiles("test")
public class CreateJWTUseCaseTest {

	@Autowired
	private CreateJWTUseCase createJWT;
	
	@Value("${api.security.token.secret}")
    private String secret;
	
	/**
	 * Should execute create jwt method and return token successfully
	 * 
	 */
	@Test
	@DisplayName("Should execute create jwt method and return token successfully")
	public void testCreateJWT() throws Exception {
		
		User user = new User(new UserDTO("test", "password"));
		
		String token = createJWT.execute(user);
		
		assertNotNull(token);
	}
	
}

package com.mirna.hospitalmanagementapi.unit.application.usecase.jwt;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.mirna.hospitalmanagementapi.HospitalManagementApiApplication;
import com.mirna.hospitalmanagementapi.application.usecase.auth.jwt.GetJWTSubjectUseCase;

/**
 * 
 * @author Mirna Gama
 * @version 1.0
 */
@SpringBootTest(classes = HospitalManagementApiApplication.class)
@TestInstance(Lifecycle.PER_CLASS)
@ActiveProfiles("test")
public class GetJWTSubjectUseCaseTest {

	@Autowired
	GetJWTSubjectUseCase getJWTSubject;

	@Value("${api.security.token.secret}")
    private String secret;
	
	String testToken;
	
	@BeforeAll
	public void init() {
		testToken = JWT.create().withIssuer("Hospital-Management-API")
				.withSubject("test")
				.withClaim("id", 1L)
				.sign(Algorithm.HMAC256(secret));
	}
	
	/**
	 * Should execute verify method and get jwt subject successfully
	 * 
	 */
	@Test
	@DisplayName("Should execute verify method and get jwt subject successfully")
	public void testGetSubject() throws Exception {
		String subject = getJWTSubject.execute(testToken);
		
		assertEquals("test", subject);
	}
	
}

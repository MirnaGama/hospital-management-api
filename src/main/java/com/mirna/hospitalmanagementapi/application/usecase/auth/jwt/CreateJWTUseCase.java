package com.mirna.hospitalmanagementapi.application.usecase.auth.jwt;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.mirna.hospitalmanagementapi.domain.entities.auth.User;

/**
 * This class is used to execute the JWT create method using Algorithm HMAC256 along with the authenticated user login and id
 * 
 * @author Mirna Gama
 * @version 1.0
 */
@Component
public class CreateJWTUseCase {

	@Value("${api.security.token.secret}")
    private String secret;
	
	/** Creates a json web token 
	 * 
	 * @param user The authenticated user
	 * @return A string containing the json web token signed with Algorithm HMAC256
	 */
	public String execute(User user) {
		Algorithm algorithm = Algorithm.HMAC256(secret);
		
		return JWT.create().withIssuer("Hospital-Management-API")
				.withSubject(user.getLogin())
				.withClaim("id", user.getId())
				.withExpiresAt(_getExpirationDate())
				.sign(algorithm);
	}
	
	/**
	 * Gets an expiration date for the token based on 2 hours duration
	 * @return Instant object containing the expiration date
	 */
	private Instant _getExpirationDate() {
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
	}
}

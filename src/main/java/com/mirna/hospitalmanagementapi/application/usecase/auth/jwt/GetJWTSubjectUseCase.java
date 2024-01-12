package com.mirna.hospitalmanagementapi.application.usecase.auth.jwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

/**
 * This class is used to execute the JWT verify method using Algorithm HMAC256 and application secret
 * 
 * @author Mirna Gama
 * @version 1.0
 */
@Component
public class GetJWTSubjectUseCase {

	@Value("${api.security.token.secret}")
    private String secret;
	
	/**
	 * Gets the jwt subject 
	 * 
	 * @param token The json web token on request header
	 * @return A string containing the subject from the decoded jwt
	 */
	public String execute(String token) {
		Algorithm algorithm = Algorithm.HMAC256(secret);
	    DecodedJWT decodedJWT = JWT.require(algorithm)
	    	.withIssuer("Hospital-Management-API")
	    	.build()
	        .verify(token);
	        
	    return decodedJWT.getSubject();
	}
}

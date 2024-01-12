package com.mirna.hospitalmanagementapi.domain.services.auth.jwt;

import com.mirna.hospitalmanagementapi.domain.entities.auth.User;

/**
 * Token service interface for managing the jwt feature
 *
 * @author Mirna Gama
 * @version 1.0
 */
public interface TokenService {

	/**
	 * Generates the authorization token
	 * 
	 * @param user The authenticated user
	 * @return A string containing the authorization token
	 */
	public String generateToken(User user);
	
	/**
	 * Gets the jwt subject 
	 * 
	 * @param token The json web token on request header
	 * @return A string containing the subject from the decoded jwt
	 */
	public String getTokenSubject(String token);

}

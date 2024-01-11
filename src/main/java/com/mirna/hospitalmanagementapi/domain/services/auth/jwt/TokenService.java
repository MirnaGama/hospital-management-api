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
	 * @returns A string containing the authorization token
	 */
	public String generateToken(User user);

}

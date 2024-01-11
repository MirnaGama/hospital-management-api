package com.mirna.hospitalmanagementapi.domain.services.auth;

import org.springframework.security.core.Authentication;

import com.mirna.hospitalmanagementapi.domain.dtos.auth.UserLoginDTO;

/**
 * Authentication service interface for managing authentication and registration.
*
 * @see User
 * @author Mirna Gama
 * @version 1.0
*/
public interface AuthService {

	/**
   	* Performs the user login
   	* 
   	* @param userLoginDTO Data transfer object containing user credentials for login
   	* @return A fully authentication object including the credentials
   	*/
	public Authentication login(UserLoginDTO userLoginDTO);
}

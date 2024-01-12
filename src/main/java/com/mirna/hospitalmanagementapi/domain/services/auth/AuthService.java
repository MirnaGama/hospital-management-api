package com.mirna.hospitalmanagementapi.domain.services.auth;

import org.springframework.security.core.Authentication;

import com.mirna.hospitalmanagementapi.domain.dtos.auth.UserDTO;
import com.mirna.hospitalmanagementapi.domain.entities.auth.User;

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
   	* @param userDTO Data transfer object containing user credentials for authentication operations
   	* @return A fully authentication object including the credentials
   	*/
	public Authentication login(UserDTO userDTO); 
	
	/**
   	* Performs the user registration
   	* 
   	* @param userDTO Data transfer object containing user credentials for authentication operations
   	* @return A user object including the credentials
   	*/
	public User register(UserDTO userDTO); 

}

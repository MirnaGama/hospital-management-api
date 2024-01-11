package com.mirna.hospitalmanagementapi.domain.services;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * User service interface for managing user information.
*
 * @see User
 * @author Mirna Gama
 * @version 1.0
*/
public interface UserService {

	/**
   	* Finds a stored user information by login.
   	* 
   	* @param id A long representing the user's system login
   	* @return The corresponding user information if successful, or null if it is non-existent.
   	*/
    public UserDetails findUserByLogin(String login);
    
}

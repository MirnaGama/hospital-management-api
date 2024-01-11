package com.mirna.hospitalmanagementapi.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.mirna.hospitalmanagementapi.application.usecase.user.FindUserByLoginUseCase;
import com.mirna.hospitalmanagementapi.domain.services.UserService;

/**
 * This class is an implementation of the UserService interface.
 *
 * This class provides methods to perform operations on User entity
 *
 * @author Mirna Gama
 * @version 1.0
 */

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private FindUserByLoginUseCase findUserByLogin;
	
	/**
   	* Finds a stored user information by login.
   	* 
   	* @param id A long representing the user's system login
   	* @return The corresponding user information if successful, or null if it is non-existent.
   	*/
	@Override
	public UserDetails findUserByLogin(String login) {
		return findUserByLogin.execute(login);
	}

}

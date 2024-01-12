package com.mirna.hospitalmanagementapi.application.services.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mirna.hospitalmanagementapi.domain.dtos.auth.UserDTO;
import com.mirna.hospitalmanagementapi.domain.entities.auth.User;
import com.mirna.hospitalmanagementapi.domain.services.UserService;
import com.mirna.hospitalmanagementapi.domain.services.auth.AuthService;

/**
 * This class is an implementation of the AuthService interface.
 *
 * This class provides methods to perform operations on users registration and authentication
 *
 * @author Mirna Gama
 * @version 1.0
 */
@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private AuthenticationManager manager;

	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	/**
   	* Performs the user login
   	* 
   	* @param userDTO Data transfer object containing user credentials for authentication operations
   	* @return A fully authentication object including the credentials
   	*/
	@Override
	public Authentication login(UserDTO userDTO) {
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDTO.login(), 
				userDTO.password());
		
		return manager.authenticate(token);
	}

	/**
   	* Performs the user registration
   	* 
   	* @param userDTO Data transfer object containing user credentials for authentication operations
   	* @return A user object including the credentials
   	*/
	@Override
	public User register(UserDTO userDTO) {
		
		String encodedPassword = passwordEncoder.encode(userDTO.password());
		userDTO = new UserDTO(userDTO.login(), encodedPassword);
		
		return this.userService.addUser(userDTO);
	}
	
}

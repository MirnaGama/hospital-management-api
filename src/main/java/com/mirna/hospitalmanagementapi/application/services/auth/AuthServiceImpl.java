package com.mirna.hospitalmanagementapi.application.services.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.mirna.hospitalmanagementapi.domain.dtos.auth.UserLoginDTO;
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

	/**
   	* Performs the user login
   	* 
   	* @param userLoginDTO Data transfer object containing user credentials for login
   	* @return A fully authentication object including the credentials
   	*/
	@Override
	public Authentication login(UserLoginDTO userLoginDTO) {
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userLoginDTO.login(), 
				userLoginDTO.password());
		
		return manager.authenticate(token);
	}
	
	
}

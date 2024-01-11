package com.mirna.hospitalmanagementapi.application.services.auth.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mirna.hospitalmanagementapi.application.usecase.auth.jwt.CreateJWTUseCase;
import com.mirna.hospitalmanagementapi.domain.entities.auth.User;
import com.mirna.hospitalmanagementapi.domain.services.auth.jwt.TokenService;

/**
 * This class is an implementation of the TokenService interface.
 *
 * This class provides methods to perform operations using the jwt (json web token) feature
 *
 * @author Mirna Gama
 * @version 1.0
 */
@Service
public class TokenServiceImpl implements TokenService {

	@Autowired
	private CreateJWTUseCase createJWT;
	
	/**
	 * Generates the authorization token 
	 * 
	 * @param user The authenticated user
	 * @returns A string containing the authorization token
	 */
	@Override
	public String generateToken(User user) {
		return createJWT.execute(user);
	}
	
}

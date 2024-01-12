package com.mirna.hospitalmanagementapi.application.usecase.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mirna.hospitalmanagementapi.domain.entities.auth.User;
import com.mirna.hospitalmanagementapi.domain.repositories.auth.UserRepository;

/**
 * This class is used to execute the save method from user repository
 * 
 * @author Mirna Gama
 * @version 1.0
 */
@Component
public class SaveUserUseCase {

	@Autowired
	private UserRepository userRepository;
	
	/**
	 * Executes the save method from User repository
	 * 
	 * @param user The User to be saved in the repository
	 * @return The saved user if successful, or null if there is an error
	 *
	 */
	public User execute(User user) {
        return this.userRepository.save(user);
    }
}

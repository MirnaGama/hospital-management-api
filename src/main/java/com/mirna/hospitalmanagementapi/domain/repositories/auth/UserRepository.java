package com.mirna.hospitalmanagementapi.domain.repositories.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.mirna.hospitalmanagementapi.domain.entities.auth.User;

/**
 * Repository interface for retrieving and manipulating all User objects using their unique Long identifier.
*
 * @author Mirna Gama
* @version 1.0
*/
public interface UserRepository extends JpaRepository<User, Long>{

	/**
	 * 
	 * @param login A string representing the user's system login
	 * @return The corresponding user information if successful, or null if it is non-existent
	 */
	UserDetails findUserByLogin(String login);
}

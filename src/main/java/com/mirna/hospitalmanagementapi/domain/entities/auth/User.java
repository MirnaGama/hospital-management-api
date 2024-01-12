package com.mirna.hospitalmanagementapi.domain.entities.auth;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.mirna.hospitalmanagementapi.domain.dtos.auth.UserDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

/**
 * @see UserDetails
 * @author Mirna Gama
 * @version 1.0
 */
@Table(name = "users")
@Entity(name = "User")
public class User implements UserDetails {

	/**
	* Constructor for class User
	* @param userDTO  Data transfer object containing User entity information
	* @see UserDTO
	*/
	public User(UserDTO userDTO) {
		this.login = userDTO.login();
		this.password = userDTO.password();
	}
	
	public User() {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "login cannot be blank")
	@Column(name = "login", unique=true)
	private String login;

	@NotBlank(message = "password cannot be blank")
	@Column(name = "password")
	private String password;
	
	/**
	 * Returns the user id.
	 * 
	 * @return A Long representing the user id.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the user id.
	 * 
	 * @param id The user's unique identifier. Must be unique.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Returns the login
	 * 
	 * @return A string representing the user's system login.
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * Sets the login
	 * 
	 * @param login Must not be blank.
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * Returns the password
	 * 
	 * @return A string representing the user's system password.
	 */
	@Override
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password
	 * 
	 * @param password Must not be blank.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority("ROLE_USER"));
	}

	/**
	 * Returns the username.
	 * 
	 * @return A string containing the user's system login
	 */
	@Override
	public String getUsername() {
		return login;
	}

	/**
	 * Checks if the account is non expired
	 * 
	 * @return true
	 */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	/**
	 * Checks if the account is non locked
	 * 
	 * @return true
	 */
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	/**
	 * Checks if the account credentials are non expired
	 * 
	 * @return true
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/**
	 * Checks if the entity is enabled
	 * 
	 * @return true
	 */
	@Override
	public boolean isEnabled() {
		return true;
	}

}

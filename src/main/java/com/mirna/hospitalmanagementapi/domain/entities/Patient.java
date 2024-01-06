package com.mirna.hospitalmanagementapi.domain.entities;

import com.mirna.hospitalmanagementapi.domain.dtos.patient.PatientDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

/**
* 
* @author Mirna Gama
* @version 1.0
*/
@Table(name="patients")
@Entity(name="Patient")
public class Patient {

	/**
	* Constructor for class Patient
	* @param patientDTO  Data transfer object containing Patient entity information
	* @see PatientDTO
	*/
	public Patient(PatientDTO patientDTO) {
		this.active=true;
		this.name=patientDTO.name();
		this.email=patientDTO.email();
		this.cpf=patientDTO.cpf();
		this.telephone=patientDTO.telephone();
		this.address = new Address(patientDTO.address());
	}
	
	public Patient(){}

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message="name cannot be blank")
	@Column(name="name")
	private String name;
	
	@NotBlank(message="email cannot be blank")
	@Column(name="email")
	private String email;
	
	@NotBlank(message="cpf cannot be blank")
	@Pattern(regexp = "\\d{11}", message = "invalid format for cpf") 
	@Column(name="cpf")
	private String cpf;
	
	@NotBlank(message="telephone cannot be blank")
	@Column(name="telephone")
	private String telephone;

	@NotNull(message="active cannot be null")
	@Column(name="_active")
	private Boolean active;
	
	@NotNull(message="address cannot be null")
	@Embedded
	private Address address;
	
	/**
	 *  Returns the doctor id.
	 * @return A Long representing the patient id.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the doctor id.
	 * @param id The patient's unique identifier.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	  * Returns the name
	 * @return A string representing the patient's name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name
	 * @param name Must not be blank.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	  * Returns the email
	 * @return A string representing the patient's email.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email
	 * @param email Must not be blank.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	  * Returns the cpf
	 * @return A string representing the patient's cpf.
	 */
	public String getCpf() {
		return cpf;
	}

	/**
	 * Sets the cpf
	 * @param cpf Must not be blank.
	 */
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	/**
	  * Returns the telephone
	 * @return A string representing the patient's telephone.
	 */
	public String getTelephone() {
		return telephone;
	}

	/**
	 * Sets the telephone
	 * @param telephone Must not be blank.
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/**
	  * Returns the active
	 * @return A boolean value that states whether the patient is active in the system
	 */
	public Boolean getActive() {
		return active;
	}

	/**
	 * Sets the active
	 * @param active Must not be null. Starts with the true value by default
	 */
	public void setActive(Boolean active) {
		this.active = active;
	}

	/**
	  * Returns the address
	 * @return An object class representing the patient's address.
	 * @see Address
	 */
	public Address getAddress() {
		return address;
	}

	/**
	 * Sets the address
	 * @param address Must not be null.
	 */
	public void setAddress(Address address) {
		this.address = address;
	}
}

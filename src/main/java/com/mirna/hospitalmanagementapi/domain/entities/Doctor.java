package com.mirna.hospitalmanagementapi.domain.entities;

import com.mirna.hospitalmanagementapi.domain.dtos.doctor.DoctorDTO;
import com.mirna.hospitalmanagementapi.domain.enums.Specialty;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
* 
* @author Mirna Gama
* @version 1.0
*/
@Table(name="doctors")
@Entity(name="Doctor")
public class Doctor {

	/**
	* Constructor for class Doctor
	* @param doctorDTO  Data transfer object containing Doctor entity information
	* @see DoctorDTO
	*/
	public Doctor(DoctorDTO doctorDTO) {
		this.active=true;
		this.name=doctorDTO.name();
		this.email=doctorDTO.email();
		this.crm=doctorDTO.crm();
		this.telephone=doctorDTO.telephone();
		this.specialty=doctorDTO.specialty();
		this.address = new Address(doctorDTO.address());
	}
	
	public Doctor(){}

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message="name cannot be blank")
	@Column(name="name")
	private String name;
	
	@NotBlank(message="email cannot be blank")
	@Column(name="email")
	private String email;
	
	@NotBlank(message="crm cannot be blank")
	@Column(name="crm", length = 6)
	private String crm;
	
	@NotBlank(message="telephone cannot be blank")
	@Column(name="telephone")
	private String telephone;
	
	@NotNull(message="specialty cannot be null")
	@Enumerated(EnumType.STRING)
	private Specialty specialty;

	@NotNull(message="active cannot be null")
	@Column(name="active")
	private Boolean active;
	
	@NotNull(message="address cannot be null")
	@Embedded
	private Address address;

	/**
	 *  Returns the doctor id.
	 * @return A Long representing the doctor id.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the doctor id.
	 * @param id The doctor's unique identifier.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	  * Returns the name
	 * @return A string representing the doctor's name.
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
	 * @return A string representing the doctor's email.
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
	  * Returns the crm
	 * @return A string representing the doctor's crm.
	 */
	public String getCrm() {
		return crm;
	}

	/**
	 * Sets the crm
	 * @param crm Must not be blank.
	 */
	public void setCrm(String crm) {
		this.crm = crm;
	}

	/**
	  * Returns the telephone
	 * @return A string representing the doctor's telephone.
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
	  * Returns the specialty
	 * @return An enum class representing the doctor's specialty.
	 * @see Specialty
	 */
	public Specialty getSpecialty() {
		return specialty;
	}

	/**
	 * Sets the specialty
	 * @param specialty Must not be null.
	 */
	public void setSpecialty(Specialty specialty) {
		this.specialty = specialty;
	}

	/**
	  * Returns the active
	 * @return A boolean value that states whether the doctor is active in the system
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
	 * @return An object class representing the doctor's address.
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

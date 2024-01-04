package com.mirna.hospitalmanagementapi.domain.entities;

import com.mirna.hospitalmanagementapi.domain.dtos.DoctorDTO;
import com.mirna.hospitalmanagementapi.domain.enums.Speciality;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
* 
* @author Mirna Gama
* @version 1.0
*/
@Table(name="doctors")
@Entity(name="Doctor")
public class Doctor {

	public Doctor(DoctorDTO doctorDTO) {
		this.name=doctorDTO.name();
		this.email=doctorDTO.email();
		this.crm=doctorDTO.email();
		this.telephone=doctorDTO.telephone();
		this.speciality=doctorDTO.speciality();
		this.address = new Address(doctorDTO.address());
	}

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String email;
	private String crm;
	private String telephone;
	
	@Enumerated(EnumType.STRING)
	private Speciality speciality;
	
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
	 * @param crn Must not be blank.
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
	  * Returns the speciality
	 * @return An enum class representing the doctor's speciality.
	 * @see Speciality
	 */
	public Speciality getSpeciality() {
		return speciality;
	}

	/**
	 * Sets the speciality
	 * @param speciality Must not be null.
	 */
	public void setSpeciality(Speciality speciality) {
		this.speciality = speciality;
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

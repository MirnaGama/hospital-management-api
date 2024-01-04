package com.mirna.hospitalmanagementapi.domain.entities;

import com.mirna.hospitalmanagementapi.domain.dtos.AddressDTO;
import com.mirna.hospitalmanagementapi.domain.dtos.DoctorDTO;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;

/**
* 
* @author Mirna Gama
* @version 1.0
*/
@Embeddable
public class Address {

	public Address() {
		
	}
	
	/**
	* Constructor for class Address
	* @param addressDTO  Data transfer object containing Address information
	* @see AddressDTO
	*/
	public Address(AddressDTO addressDTO) {
		this.street = addressDTO.street();
		this.neighborhood = addressDTO.neighborhood();
		this.zipCode = addressDTO.zipCode();
		this.city = addressDTO.city();
		this.state = addressDTO.state();
		this.additionalDetails = addressDTO.additionalDetails();
		this.houseNumber = addressDTO.houseNumber();
	}
	
	@NotBlank(message="street cannot be blank")
	private String street;
	
	@NotBlank(message="neighborhood cannot be blank")
	private String neighborhood;
	
	@NotBlank(message="zipCode cannot be blank")
	private String zipCode;
	
	@NotBlank(message="city cannot be blank")
	private String city;
	
	@NotBlank(message="state cannot be blank")
	private String state;
	
	private String additionalDetails;
	
	private String houseNumber;
	
	/**
	 * Returns the street
	 * @return A string representing the street.
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * Sets the street
	 * @param street Must not be blank.
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * Returns the neighborhood
	 * @return A string representing the neighborhood.
	 */
	public String getNeighborhood() {
		return neighborhood;
	}

	/**
	 * Sets the neighborhood
	 * @param neighborhood Must not be blank.
	 */
	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	/**
	 * Returns the zip code
	 * @return A string representing the zip code.
	 */
	public String getZipCode() {
		return zipCode;
	}

	/**
	 * Sets the zip code
	 * @param zipCode Must not be blank.
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	/**
	  * Returns the city
	 * @return A string representing the city.
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Sets the city
	 * @param city Must not be blank.
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	  * Returns the state
	 * @return A string representing the state.
	 */
	public String getState() {
		return state;
	}

	/**
	 * Sets the state
	 * @param state Must not be blank.
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	  * Returns the additional details 
	 * @return A string representing the additional details.
	 */
	public String getAdditionalDetails() {
		return additionalDetails;
	}

	/**
	 * Sets the additional details
	 * @param additionalDetails Not required
	 */
	public void setAdditionalDetails(String additionalDetails) {
		this.additionalDetails = additionalDetails;
	}

	/**
	  * Returns the house number
	 * @return A string representing the house number.
	 */
	public String getHouseNumber() {
		return houseNumber;
	}


	/**
	 * Sets the house number
	 * @param houseNumber Not required
	 */
	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}
	
}

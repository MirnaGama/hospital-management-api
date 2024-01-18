package com.mirna.hospitalmanagementapi.domain.entities;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mirna.hospitalmanagementapi.domain.enums.ReasonCancellation;
import com.mirna.hospitalmanagementapi.domain.enums.Specialty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

/**
* 
* @author Mirna Gama
* @version 1.0
*/
@Table(name="consultations")
@Entity(name="Consultation")
public class Consultation {
	
	/**
	* Constructor for class Consultation
	* @param patient Patient who scheduled the consultation
	* @param doctor Doctor who will be at the consultation
	* @param consultationDate Scheduled date for the consultation
	*/
	public Consultation(Patient patient, Doctor doctor, LocalDateTime consultationDate) {
		this.patient=patient;
		this.doctor=doctor;
		this.consultationDate=consultationDate;
		this.canceled=false;
	}
	
	public Consultation() {}
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message="consultationDate cannot be null")
	@Column(name="consultation_date")
	private LocalDateTime consultationDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
	@JoinColumn(name="patient_id")
	private Patient patient;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
	@JoinColumn(name="doctor_id")
	private Doctor doctor;
	
	@Column(name="canceled")
	private boolean canceled;
	
	@Column(name="reason_cancellation")
	@Enumerated(EnumType.STRING)
	private ReasonCancellation reasonCancellation;

	/**
	 *  Returns the consultation id.
	 * @return A Long representing the consultation id.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the consultation id.
	 * @param id The consultation's unique identifier.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	  * Returns the consultation date
	 * @return A local date time object representing the consultation date
	 */
	public LocalDateTime getConsultationDate() {
		return consultationDate;
	}

	/**
	 * Sets the consultation date.
	 * @param consultationDate Scheduled date for the consultation
	 */
	public void setConsultationDate(LocalDateTime consultationDate) {
		this.consultationDate = consultationDate;
	}

	/**
	  * Returns the patient
	 * @return A Patient entity representing the patient
	 */
	public Patient getPatient() {
		return patient;
	}

	/**
	 * Sets the patient.
	 * @param patient Patient who scheduled the consultation
	 */
	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	/**
	  * Returns the doctor
	 * @return A Doctor entity representing the doctor
	 */
	public Doctor getDoctor() {
		return doctor;
	}

	/**
	 * Sets the doctor.
	 * @param doctor Doctor who will be at the consultation
	 */
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	/**
	  * Returns the canceled
	 * @return A boolean value that states whether the consultation is canceled in the system
	 */
	public boolean isCanceled() {
		return canceled;
	}

	/**
	 * Sets the canceled
	 * @param canceled Must not be null. Starts with the false value by default
	 */
	public void setCanceled(boolean canceled) {
		this.canceled = canceled;
	}

	/**
	  * Returns the reason of consultation cancellation
	 * @return An enum class representing the reason of cancellation.
	 * @see ReasonCancellation
	 */
	public ReasonCancellation getReasonCancellation() {
		return reasonCancellation;
	}

	/**
	 * Sets the reason of consultation cancellation
	 * @param reasonCancellation 
	 */
	public void setReasonCancellation(ReasonCancellation reasonCancellation) {
		this.reasonCancellation = reasonCancellation;
	}
	
}

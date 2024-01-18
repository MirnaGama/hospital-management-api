package com.mirna.hospitalmanagementapi.application.services;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mirna.hospitalmanagementapi.application.usecase.consultation.FindConsultationByDoctorAndDateUseCase;
import com.mirna.hospitalmanagementapi.application.usecase.consultation.FindConsultationByIdUseCase;
import com.mirna.hospitalmanagementapi.application.usecase.consultation.FindConsultationByPatientAndDateUseCase;
import com.mirna.hospitalmanagementapi.application.usecase.consultation.SaveConsultationUseCase;
import com.mirna.hospitalmanagementapi.application.usecase.doctor.FindDoctorByIdUseCase;
import com.mirna.hospitalmanagementapi.application.usecase.doctor.FindOneFreeDoctorBySpecialtyUseCase;
import com.mirna.hospitalmanagementapi.application.usecase.patient.FindPatientByIdUseCase;
import com.mirna.hospitalmanagementapi.domain.dtos.consultation.ConsultationCanceledDTO;
import com.mirna.hospitalmanagementapi.domain.dtos.consultation.ConsultationDTO;
import com.mirna.hospitalmanagementapi.domain.entities.Consultation;
import com.mirna.hospitalmanagementapi.domain.entities.Doctor;
import com.mirna.hospitalmanagementapi.domain.entities.Patient;
import com.mirna.hospitalmanagementapi.domain.exceptions.ConsultationValidationException;
import com.mirna.hospitalmanagementapi.domain.services.ConsultationService;

import jakarta.persistence.EntityNotFoundException;

/**
 * This class is an implementation of the ConsultationService interface.
 *
 * This class provides methods to perform operations on consultations
 *
 * @author Mirna Gama
 * @version 1.0
 */
@Service
public class ConsultationServiceImpl implements ConsultationService {

	@Autowired
	private SaveConsultationUseCase saveConsultation;

	@Autowired
	private FindConsultationByIdUseCase findConsultationById;
	
	@Autowired
	private FindConsultationByDoctorAndDateUseCase findConsultationByDoctorAndDate;

	@Autowired
	private FindConsultationByPatientAndDateUseCase findConsultationByPatientAndDate;

	@Autowired
	private FindPatientByIdUseCase findPatientById;

	@Autowired
	private FindDoctorByIdUseCase findDoctorById;

	@Autowired
	private FindOneFreeDoctorBySpecialtyUseCase findOneFreeDoctorBySpecialty;

	/**
	* Adds a new consultation to the repository.
	* 
	* @param consultationDTO A data transfer object representing a consultation to add.
	* @return The saved consultation if successful,  or throws an exception if there is an error.
	 * @throws ConsultationValidationException if there is a validation error
	*/
	@Override
	public Consultation addConsultation(ConsultationDTO consultationDTO) throws ConsultationValidationException {

		Patient patient = findPatientById.execute(consultationDTO.patientId());

		if (!patient.getActive())
			throw new ConsultationValidationException("This patient is not active");

		if (findConsultationByPatientAndDate.execute(patient.getId(), consultationDTO.consultationDate()) != null)
			throw new ConsultationValidationException("This patient is not free on this date");

		Doctor doctor = null;

		if (consultationDTO.doctorId() != null) {

			doctor = findDoctorById.execute(consultationDTO.doctorId());

			if (!doctor.getActive())
				throw new ConsultationValidationException("This doctor is not active");

			if (findConsultationByDoctorAndDate.execute(doctor.getId(), consultationDTO.consultationDate()) != null)
				throw new ConsultationValidationException("This doctor is not free on this date");

		} else if (consultationDTO.specialty() != null) {

			doctor = findOneFreeDoctorBySpecialty.execute(consultationDTO.specialty(),
					consultationDTO.consultationDate());

			if (doctor == null) throw new ConsultationValidationException("There is no free doctor for this date with this specialty");
			
		} else {
			throw new ConsultationValidationException("At least the specialty or doctor ID must be filled in");
		}

		Consultation consultation = new Consultation(patient, doctor, consultationDTO.consultationDate());

		return saveConsultation.execute(consultation);
	}

	/**
   	* Finds a stored consultation by id.
   	* 
   	* @param id A long representing the consultation's unique identifier
   	* @return The corresponding consultation if successful, or throws an exception if it is non-existent.
   	*/
	@Override
	public Consultation findConsultationById(Long id) {
		Consultation consultation = findConsultationById.execute(id);

		if (consultation == null)
			throw new EntityNotFoundException("No existing consultation with this id");
		
		return consultation;
	}
	
	/**
	 * Cancels and updates an existing query in the repository
	 * @param consultationCanceledDTO A data transfer object representing the consultation that will be canceled.
	* @return The canceled consultation if successful,  or throws an exception if there is an error.
	 */
	@Override
	public Consultation cancelConsultation(ConsultationCanceledDTO consultationCanceledDTO) {
		Consultation consultation = this.findConsultationById(consultationCanceledDTO.consultationId());

		consultation.setCanceled(true);
		consultation.setReasonCancellation(consultationCanceledDTO.reasonCancellation());

		return saveConsultation.execute(consultation);
	}

}

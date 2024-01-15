package com.mirna.hospitalmanagementapi.unit.application.usecase.doctor;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.mirna.hospitalmanagementapi.HospitalManagementApiApplication;
import com.mirna.hospitalmanagementapi.application.usecase.doctor.FindOneFreeDoctorBySpecialtyUseCase;
import com.mirna.hospitalmanagementapi.domain.dtos.AddressDTO;
import com.mirna.hospitalmanagementapi.domain.dtos.doctor.DoctorDTO;
import com.mirna.hospitalmanagementapi.domain.entities.Doctor;
import com.mirna.hospitalmanagementapi.domain.enums.Specialty;
import com.mirna.hospitalmanagementapi.domain.repositories.DoctorRepository;

/**
 * 
 * @author Mirna Gama
 * @version 1.0
 */
@SpringBootTest(classes = HospitalManagementApiApplication.class)
@TestInstance(Lifecycle.PER_CLASS)
@ActiveProfiles("test")
public class FindOneFreeDoctorBySpecialtyUseCaseTest {

	@Autowired
	private FindOneFreeDoctorBySpecialtyUseCase findOneFreeDoctorBySpecialty;
	
	@Autowired
	private DoctorRepository doctorRepository;
	
	@BeforeAll
	public void init() {
		DoctorDTO doctorDTO = new DoctorDTO("test1", "test1@gmail.com", "123456", "99999999", Specialty.ORTHOPEDICS,
				new AddressDTO("TEST STREET", "NEIGHBORHOOD", "12345678", "CITY", "ST", null, null));
		
		doctorRepository.save(new Doctor(doctorDTO));
	}
	
	@AfterAll
	public void terminate() {
		doctorRepository.deleteAll();
	}
	
	/**
	 * Should execute findOneFreeDoctorBySpecialty method
	 * 
	 */
	@Test
	@DisplayName("Should execute findOneFreeDoctorBySpecialty method with pagination")
	public void testfindOneFreeDoctorBySpecialty() throws Exception {
		
		Doctor doctor = findOneFreeDoctorBySpecialty.execute(Specialty.ORTHOPEDICS, LocalDateTime.now());

		assertEquals(doctor.getSpecialty(), Specialty.ORTHOPEDICS);
	}
}

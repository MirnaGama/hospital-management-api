package com.mirna.hospitalmanagementapi.unit.application.usecase.doctor;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import com.mirna.hospitalmanagementapi.HospitalManagementApiApplication;
import com.mirna.hospitalmanagementapi.application.usecase.doctor.FindDoctorByIdUseCase;
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
public class FindDoctorByIdUseCaseTest {

	@Autowired
	private FindDoctorByIdUseCase findDoctorById;
	
	@Autowired
	private DoctorRepository doctorRepository;
	
	private Doctor testDoctor;
	
	@BeforeAll
	public void init() {
		DoctorDTO doctorDTO1 = new DoctorDTO("test1", "test1@gmail.com", "123456", "99999999", Specialty.ORTHOPEDICS,
				new AddressDTO("TEST STREET", "NEIGHBORHOOD", "12345678", "CITY", "ST", null, null));
	
		testDoctor = doctorRepository.save(new Doctor(doctorDTO1));
	}
	
	@AfterAll
	public void terminate() {
		doctorRepository.deleteAll();
	}
	
	/**
	 * Should execute findById method successfully
	 * 
	 */
	@Test
	@DisplayName("Should execute findById method")
	public void testFindDoctorById() throws Exception {
		
		Doctor doctor = findDoctorById.execute(testDoctor.getId());

		assertNotNull(doctor);
	}
}

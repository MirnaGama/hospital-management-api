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
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name="doctors")
@Entity(name="Doctor")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Doctor {

	public Doctor(DoctorDTO doctorDTO) {
		this.name=doctorDTO.name();
		this.email=doctorDTO.email();
		this.crm=doctorDTO.email();
		this.telephone=doctorDTO.telephone();
		this.address = new Address(doctorDTO.addressDTO());
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
	
}

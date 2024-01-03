package com.mirna.hospitalmanagementapi.domain.dtos;

import com.mirna.hospitalmanagementapi.domain.enums.Speciality;

public record DoctorDTO(String name, String email, String crm, String telephone, Speciality speciality, AddressDTO addressDTO) {

}

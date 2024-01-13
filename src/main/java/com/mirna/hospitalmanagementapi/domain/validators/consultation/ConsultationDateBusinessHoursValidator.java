package com.mirna.hospitalmanagementapi.domain.validators.consultation;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

import com.mirna.hospitalmanagementapi.domain.validators.consultation.constraints.ConsultationDateBusinessHours;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.constraints.Future;

public class ConsultationDateBusinessHoursValidator implements ConstraintValidator <ConsultationDateBusinessHours, LocalDateTime> {

	private Long businessHourStart;
	private Long businessHourEnd;
	
	@Override
	public void initialize(ConsultationDateBusinessHours consultationDateBusinessHours) {
		businessHourStart = 7L;
		businessHourEnd = 18L;
	}
	 
	
	@Override
	public boolean isValid(LocalDateTime value, ConstraintValidatorContext context) {
		
		if (value.getDayOfWeek().equals(DayOfWeek.SUNDAY) 
				|| value.getHour() < businessHourStart 
				|| value.getHour() > businessHourEnd) return false;
		
		return true;
	}

}

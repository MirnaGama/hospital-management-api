package com.mirna.hospitalmanagementapi.domain.validators.consultation.constraints;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.mirna.hospitalmanagementapi.domain.validators.consultation.ConsultationDateBusinessHoursValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = ConsultationDateBusinessHoursValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ConsultationDateBusinessHours {

	String message() default "Invalid consultation date. The business hours are Monday to Saturday, from 07:00 to 19:00";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    
}

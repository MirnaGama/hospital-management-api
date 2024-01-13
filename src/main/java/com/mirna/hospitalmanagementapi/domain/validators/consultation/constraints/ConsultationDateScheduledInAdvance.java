package com.mirna.hospitalmanagementapi.domain.validators.consultation.constraints;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.mirna.hospitalmanagementapi.domain.validators.consultation.ConsultationDateScheduledInAdvanceValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = ConsultationDateScheduledInAdvanceValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ConsultationDateScheduledInAdvance {

	String message() default "This consultation must be scheduled at least 30 minutes in advance";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

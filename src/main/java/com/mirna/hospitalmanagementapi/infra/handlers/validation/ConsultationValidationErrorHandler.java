package com.mirna.hospitalmanagementapi.infra.handlers.validation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mirna.hospitalmanagementapi.domain.exceptions.ConsultationValidationException;

/**
 * This @RestControllerAdvice is used to handle errors related to consultation business rules and return appropriate response message
 * 
 * @author Mirna Gama
 * @version 1.0
 */
@RestControllerAdvice
public class ConsultationValidationErrorHandler {

	@ExceptionHandler(ConsultationValidationException.class) 
	public ResponseEntity<Object> handle(ConsultationValidationException exception) {
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}
}

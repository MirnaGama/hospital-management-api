package com.mirna.hospitalmanagementapi.infra.handlers.validation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * This @RestControllerAdvice is used to handle validation errors and return appropriate response messages
 * 
 * @author Mirna Gama
 * @version 1.0
 */
@RestControllerAdvice
public class ValidationErrorHandler {

	@Autowired
	private MessageSource messageSource;
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class) 
	public ResponseEntity<Object> handle(MethodArgumentNotValidException expection) {
		
		List<String> validationErrors = new ArrayList<>();
	
		List<FieldError> fieldErrors = expection.getBindingResult().getFieldErrors(); 
		
		for(FieldError fieldError : fieldErrors) {
			String validationError = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
			validationErrors.add(validationError);
		}
		
		Map<String, Object> body = new HashMap<>();
		body.put("errors", validationErrors);

		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}
	
}
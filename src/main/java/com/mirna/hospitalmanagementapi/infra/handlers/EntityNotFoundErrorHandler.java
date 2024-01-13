package com.mirna.hospitalmanagementapi.infra.handlers;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

/**
 * This @RestControllerAdvice is used to handle entity not found error and return appropriate response message
 * 
 * @author Mirna Gama
 * @version 1.0
 */
@RestControllerAdvice
public class EntityNotFoundErrorHandler {
	
	@ExceptionHandler({EntityNotFoundException.class}) 
	public ResponseEntity<Object> handle(EntityNotFoundException exception) {
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
	}
}

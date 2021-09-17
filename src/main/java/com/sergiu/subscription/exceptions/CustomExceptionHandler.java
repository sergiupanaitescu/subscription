package com.sergiu.subscription.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);

	
	@ExceptionHandler(value = { ObjectNotFoundException.class })
	public ResponseEntity<Object> handleObjectNotFound(Exception e) {
		logger.error("Object not found!: ", e.getMessage());
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = { GenericRuntimeMessageException.class })
	public ResponseEntity<Object> handleGenericRuntimeMessageException(Exception e) {
		logger.error("GenericException!: ", e.getMessage());
		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = { DuplicateSubscriptionException.class })
	public ResponseEntity<Object> handleDuplicateSubscription(Exception e) {
		logger.error("Duplicate subscription per user!: ", e.getMessage());
		return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
	}

	@ExceptionHandler(value = { MethodArgumentNotValidException.class })
	public ResponseEntity<Object> handleValidationException(Exception e) {
		logger.error("Validation exception!: ", e.getMessage());
		return new ResponseEntity<>("Request validation exception! Check mandatory params!", HttpStatus.BAD_REQUEST);//maybe find a better status
	}
	
	@ExceptionHandler(value = { Exception.class })
	public ResponseEntity<Object> handleInvalidInputException(Exception e) {
		logger.error("Unexpected error!: ", e.getMessage());
		return new ResponseEntity<>("Unexpected error!", HttpStatus.NOT_ACCEPTABLE);//maybe find a better status
	}

}

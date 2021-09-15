package com.sergiu.subscription.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);

	@ExceptionHandler(value = { Exception.class })
	public ResponseEntity<Object> handleInvalidInputException(Exception e) {
		logger.error("Unexpected error!: ", e.getMessage());
		return new ResponseEntity<Object>("Unexpected error!", HttpStatus.INTERNAL_SERVER_ERROR);
	}

}

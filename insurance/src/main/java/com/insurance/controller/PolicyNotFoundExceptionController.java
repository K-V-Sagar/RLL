package com.insurance.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.insurance.exceptions.PolicyNotFoundException;

@ControllerAdvice
public class PolicyNotFoundExceptionController {

	@ExceptionHandler(value=PolicyNotFoundException.class)
	public ResponseEntity<Object> handleException(PolicyNotFoundException ex) {
		return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}
}
	
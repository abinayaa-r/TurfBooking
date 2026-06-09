package com.turf.admin.utility;

import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.turf.admin.exception.TurfAdminException;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class ExceptionControllerAdvice {

	private static final Logger logger = LogManager.getLogger(ExceptionControllerAdvice.class);
	
	@Autowired
	Environment environment;
	
	@ExceptionHandler(TurfAdminException.class)
	public ResponseEntity<ErrorInfo> turfUserExceptionHandler(TurfAdminException exception){
		logger.error(" in RestCntrller Advice turf " +exception.getMessage(),exception);
		ErrorInfo info = new ErrorInfo();
		info.setErrorCode(HttpStatus.BAD_REQUEST.value());
		info.setErrorMessage(environment.getProperty(exception.getMessage()));
		return new ResponseEntity<>(info,HttpStatus.BAD_REQUEST);
	
	}

	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorInfo> generalException(Exception exception){
		logger.error(" in RestCntrller Advice general" +exception.getMessage(),exception);
		ErrorInfo info = new ErrorInfo();
		info.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		info.setErrorMessage(environment.getProperty("General_Exception"));
		return new ResponseEntity<>(info,HttpStatus.INTERNAL_SERVER_ERROR);
	
	}
	
	@ExceptionHandler({MethodArgumentNotValidException.class,ConstraintViolationException.class})
	public ResponseEntity<ErrorInfo> turfUserExceptionHandler(Exception exception){
		logger.error(" in RestCntrller Advice methodArgument" +exception.getMessage(),exception);
		String errorMsg;
		
		if(exception instanceof MethodArgumentNotValidException ) {
			MethodArgumentNotValidException method = (MethodArgumentNotValidException) exception;
			errorMsg = method.getBindingResult().getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(", "));
		}else {
			ConstraintViolationException constraint = (ConstraintViolationException) exception;
			errorMsg = constraint.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(", "));
		}
		ErrorInfo info = new ErrorInfo();
		info.setErrorCode(HttpStatus.BAD_REQUEST.value());
		info.setErrorMessage(environment.getProperty(errorMsg));
		return new ResponseEntity<>(info,HttpStatus.BAD_REQUEST);
	}
}

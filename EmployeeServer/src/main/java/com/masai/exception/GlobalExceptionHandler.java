package com.masai.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;


@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MyErrorDetails> myMNVFHandler(MethodArgumentNotValidException mnv){
		
		MyErrorDetails err = new MyErrorDetails();
		
		err.setMessage("Validation Error !");
		err.setTimestamp(LocalDateTime.now());
		err.setDescription(mnv.getBindingResult().getFieldError().getDefaultMessage());
		
		return new ResponseEntity<MyErrorDetails>(err,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(EmployeeException.class)
	public ResponseEntity<MyErrorDetails> empExceptionHandler(EmployeeException empExc, WebRequest req){
		
		MyErrorDetails err = new MyErrorDetails();
		
		err.setMessage(empExc.getMessage());
		err.setTimestamp(LocalDateTime.now());
		err.setDescription(req.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(err,HttpStatus.PARTIAL_CONTENT);
		
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<MyErrorDetails> generalExceptionHandler(Exception exc,  WebRequest req){
		
		MyErrorDetails err = new MyErrorDetails();
		
		err.setMessage(exc.getMessage());
		err.setTimestamp(LocalDateTime.now());
		err.setDescription(req.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(err,HttpStatus.BAD_REQUEST);
		
	}
}

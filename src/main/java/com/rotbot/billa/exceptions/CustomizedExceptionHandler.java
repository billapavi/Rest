package com.rotbot.billa.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@ControllerAdvice
@RestController
public class CustomizedExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) throws Exception 
		{
			ExceptionResponse ersponse = new ExceptionResponse("UserNotFounExcetpion", new Date(), ex.getMessage()
					+ "");
			request.getDescription(false);
			return new ResponseEntity<Object>(ersponse, HttpStatus.EXPECTATION_FAILED);
		}
}

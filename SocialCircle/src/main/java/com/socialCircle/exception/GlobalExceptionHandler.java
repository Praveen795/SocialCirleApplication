package com.socialCircle.exception;


import java.nio.file.AccessDeniedException;
import java.util.Date;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.socialCircle.entityDTO.ErrorDetails;
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	/*
	 * handle ResourceNotFoundException 
	 */
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorDetails> handlingResourceNotFoundException(
			ResourceNotFoundException resourceNotFoundException, WebRequest webRequest) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), resourceNotFoundException.getMessage(),
				webRequest.getDescription(false));

		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);

	}
	/*
	 * handle SocialCircleException
	 */
	@ExceptionHandler(SocialCircleException.class)
	public ResponseEntity<ErrorDetails> handlingSocialCircleException(SocialCircleException socialCircleException,
			WebRequest webRequest) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), socialCircleException.getMessage(),
				webRequest.getDescription(false));

		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.BAD_REQUEST);

	}
	/*
	 * handle GlobalException
	 */
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ErrorDetails> handlingException(RuntimeException exception,WebRequest webRequest){
		ErrorDetails errorDetails=new ErrorDetails(new Date(),exception.getMessage(),webRequest.getDescription(false));
		
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	/*
	 * AccessDeniedException
	 */
	
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<ErrorDetails> handlingAccessDeniedException(AccessDeniedException exception,WebRequest webRequest){
		ErrorDetails errorDetails=new ErrorDetails(new Date(),exception.getMessage(),webRequest.getDescription(false));
		
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}

package com.socialCircle.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class SocialCircleException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpStatus status;
	private String massage;
	
	public SocialCircleException(HttpStatus status, String massage) {
		super(String.format(massage));
		this.status = status;
		this.massage = massage;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public String getMassage() {
		return massage;
	}
	
	
	
	

}

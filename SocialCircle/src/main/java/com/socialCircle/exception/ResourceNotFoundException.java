package com.socialCircle.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3729513858834843815L;
	private String resourceName;
	private String fieldName;
	private long id;
	
	
	
	public ResourceNotFoundException(String resourceName, String fieldName, long id) {
		super(String.format("%s not found %s : %s",resourceName,fieldName,id));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.id = id;
	}
	
	public String getResourceName() {
		return resourceName;
	}
	public String getFieldName() {
		return fieldName;
	}
	public long getId() {
		return id;
	}
	
	
	
	
	

}

package com.socialCircle.entityDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CommentDto {
	
	private long commentId;
	@NotEmpty(message = "name should not be empty or null")
	private String name;
	@NotNull(message = "email should not be null")
	@Email
	private String email;
	@Size(min = 10)
	private String body;
	
	public CommentDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CommentDto(long commentId, String name, String email, String body) {
		super();
		this.commentId = commentId;
		this.name = name;
		this.email = email;
		this.body = body;
		
	}
	public long getCommentId() {
		return commentId;
	}
	public void setCommentId(long commentId) {
		this.commentId = commentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	
	
	
	
	
}

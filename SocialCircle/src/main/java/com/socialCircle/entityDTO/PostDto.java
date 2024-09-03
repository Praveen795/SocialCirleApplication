package com.socialCircle.entityDTO;

import java.util.Set;

import jakarta.validation.constraints.NotEmpty;





public class PostDto {
	
	private long postId;
	@NotEmpty(message = "title should not be null")
	private String title;
	@NotEmpty(message = "content should not be null")
	private String description;
	@NotEmpty(message = "content should not be null")
	private String content;
	private Set<CommentDto> comments;
	public PostDto() {
		super();
		// TODO Auto-generated constructor stub
	}


	
	
	public PostDto(long postId, String title, String description, String content, Set<CommentDto> comments) {
		super();
		this.postId = postId;
		this.title = title;
		this.description = description;
		this.content = content;
		this.comments = comments;
	}
	
	




	public Set<CommentDto> getComments() {
		return comments;
	}




	public void setComments(Set<CommentDto> comments) {
		this.comments = comments;
	}




	public long getPostId() {
		return postId;
	}

	public void setPostId(long postId) {
		this.postId = postId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
	
	
	
	


}

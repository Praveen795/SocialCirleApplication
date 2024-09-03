package com.socialCircle.service;


import com.socialCircle.entityDTO.PostDto;
import com.socialCircle.entityDTO.PostResponse;

public interface PostService {
	
	PostDto getPostById(long postId);
	
	PostResponse getAllPosts(int pageNumber, int pageSize, String sortBy);
	
	PostDto createPost(PostDto postDto);
	
	PostDto updatePost(PostDto postDto, long postId);
	
	String deletePost(long postId);
	
	
	

}

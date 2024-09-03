package com.socialCircle.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.socialCircle.entityDTO.PostDto;
import com.socialCircle.entityDTO.PostResponse;
import com.socialCircle.service.PostService;
import com.socialCircle.util.AppContent;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/posts")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@GetMapping("/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable long postId){
		PostDto postDto= postService.getPostById(postId);
		return new ResponseEntity<PostDto>(postDto, HttpStatus.OK);
		
		
	}
	@GetMapping
	public ResponseEntity<PostResponse> getAllPosts(
	    @RequestParam(defaultValue = AppContent.DEFAULT_PAGE_NUMBER,required = false) int pageNumber,
	    @RequestParam(defaultValue = AppContent.DEFAULT_PAGE_SIZE,required = false) int pageSize,
	    @RequestParam(defaultValue = AppContent.DEFAULT_SORTBY,required = false) String sortBy){
		PostResponse allPosts = postService.getAllPosts(pageNumber,pageSize,sortBy);
		return new ResponseEntity<PostResponse>(allPosts,HttpStatus.OK);
		
	}
	@PostMapping
	public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto){
		PostDto post = postService.createPost(postDto);
		return new ResponseEntity<PostDto>(post,HttpStatus.CREATED);
	}
	
	@PutMapping("/{postId}")
	public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto , @PathVariable long postId){
		PostDto updatePost = postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
		
	}
	@DeleteMapping("/{postId}")
	public ResponseEntity<String> deletePost(@PathVariable long postId){
		String deletePost = postService.deletePost(postId);
		return new ResponseEntity<String>(deletePost,HttpStatus.OK);
		
		
	}

}

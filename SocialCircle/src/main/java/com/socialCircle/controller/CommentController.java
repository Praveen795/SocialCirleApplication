package com.socialCircle.controller;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.socialCircle.entityDTO.CommentDto;
import com.socialCircle.service.CommentService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@PostMapping("/posts/{postId}/comments")
	public ResponseEntity<CommentDto> createComment(@PathVariable long postId, @Valid @RequestBody CommentDto commentDto){
		CommentDto outputCommentDto = commentService.createComment(postId, commentDto);
		return new ResponseEntity<CommentDto>(outputCommentDto,HttpStatus.CREATED);
		
	}
	
	@GetMapping("/posts/{postId}/comments/{commentId}")
	public ResponseEntity<CommentDto> getCommentByCommentId(@PathVariable long postId,@PathVariable long commentId){
		CommentDto commentDto = commentService.getCommentByCommentId(postId, commentId);
		return new ResponseEntity<CommentDto>(commentDto,HttpStatus.OK);
	}
	
	@GetMapping("/posts/{postId}/comments")
	public ResponseEntity<Set<CommentDto>> getAllCommentsByPostId(@PathVariable long postId){
		Set<CommentDto> commentsByPostId = commentService.getAllCommentsByPostId(postId);
		return new ResponseEntity<Set<CommentDto>>(commentsByPostId,HttpStatus.OK);
	}
	
	
	@PutMapping("/posts/{postId}/comments/{commentId}")
	public ResponseEntity<CommentDto> updateComment(@PathVariable long postId, @PathVariable long commentId,@Valid @RequestBody CommentDto commentDto){
		CommentDto updateCommentDto = commentService.updateComment(postId, commentId, commentDto);
		return new ResponseEntity<CommentDto>(updateCommentDto,HttpStatus.OK);
	}
	
	@DeleteMapping("/posts/{postId}/comments/{commentId}")
	public ResponseEntity<String> deleteComment(@PathVariable long postId, @PathVariable long commentId){
		String deleteComment = commentService.deleteComment(postId, commentId);
		return new ResponseEntity<String>(deleteComment,HttpStatus.OK);
	}
	


}

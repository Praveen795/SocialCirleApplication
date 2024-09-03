package com.socialCircle.service;

import java.util.Set;

import com.socialCircle.entityDTO.CommentDto;

public interface CommentService {
	
	CommentDto createComment(long postId,CommentDto commentDto);
	
	CommentDto getCommentByCommentId(long postId,long commentId);
	
	Set<CommentDto> getAllCommentsByPostId(long postId);
	
	CommentDto updateComment(long postId,long commentId,CommentDto commentDto);
	
	String deleteComment(long postId,long commentId);

}

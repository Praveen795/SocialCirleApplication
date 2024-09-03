package com.socialCircle.serviceImpl;


import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.socialCircle.Dao.CommentRepository;
import com.socialCircle.Dao.PostRepository;
import com.socialCircle.entity.Comment;
import com.socialCircle.entity.Post;
import com.socialCircle.entityDTO.CommentDto;
import com.socialCircle.exception.ResourceNotFoundException;
import com.socialCircle.exception.SocialCircleException;
import com.socialCircle.service.CommentService;

@Service
public class CommnetServiceImpl implements CommentService {
	@Autowired
	private CommentRepository commentRepository;
	@Autowired
	private PostRepository postRepository;

	@Override
	public CommentDto createComment(long postId, CommentDto commentDto) {
		
		Post post = postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "postId", postId));
		Comment comment = commentDtoToComment(commentDto);
		comment.setPost(post);
		Comment savedComment= commentRepository.save(comment);
		CommentDto outputCommentDto = commentToCommentDto(savedComment);
		return outputCommentDto;
	}

	@Override
	public CommentDto getCommentByCommentId(long postId, long commentId) {
		Post post = postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "PostId", postId));
		Comment comment = commentRepository.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Commenet", "commenetId", commentId));
		if(comment.getPost().getPostId()!=post.getPostId()) {
			throw new SocialCircleException(HttpStatus.BAD_REQUEST, "comment does not belongs to this post");
		}
		CommentDto commentDto = commentToCommentDto(comment);
		return commentDto;
	}

	@Override
	public Set<CommentDto> getAllCommentsByPostId(long postId) {
		Post post = postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "PostId", postId));
		Set<Comment> comments = post.getComments();
		Set<CommentDto> commentDtoList = comments.stream().map(comment->commentToCommentDto(comment)).collect(Collectors.toSet());
		return commentDtoList;
	}

	@Override
	public CommentDto updateComment(long postId, long commentId, CommentDto commentDto) {
		Post post = postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "PostId", postId));
		Comment comment = commentRepository.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment", "CommentId", commentId));
		if(post.getPostId()!=comment.getPost().getPostId()) {
			throw new SocialCircleException(HttpStatus.BAD_REQUEST, "comment not belongs to this post");
		}
		comment.setBody(commentDto.getBody());
		comment.setEmail(commentDto.getEmail());
		comment.setName(commentDto.getName());
		comment.setPost(post);
		Comment savedComment = commentRepository.save(comment);
		CommentDto outputcommentDto = commentToCommentDto(savedComment);
		
		return outputcommentDto;
	}

	@Override
	public String deleteComment(long postId, long commentId) {
		Post post = postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "PostId", postId));
		Comment comment = commentRepository.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment", "CommentId", commentId));
		if(post.getPostId()!=comment.getPost().getPostId()) {
			throw new SocialCircleException(HttpStatus.BAD_REQUEST, "comment not belongs to this post");
		}
		commentRepository.delete(comment);
		return "deleted comment successfully";
	}
	
	private CommentDto commentToCommentDto(Comment comment) {
		CommentDto commentDto=new CommentDto();
		commentDto.setCommentId(comment.getCommentId());
		commentDto.setName(comment.getName());
		commentDto.setBody(comment.getBody());
		commentDto.setEmail(comment.getEmail());
		
		return commentDto;
	}
	
	private Comment commentDtoToComment(CommentDto commentDto) {
		Comment comment=new Comment();
		comment.setCommentId(commentDto.getCommentId());
		comment.setName(commentDto.getName());
		comment.setBody(commentDto.getBody());
		comment.setEmail(commentDto.getEmail());
		
		return comment;
	}


}

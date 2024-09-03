package com.socialCircle.serviceImpl;

import java.util.List;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.socialCircle.Dao.PostRepository;

import com.socialCircle.entity.Post;
import com.socialCircle.entityDTO.PostDto;
import com.socialCircle.entityDTO.PostResponse;
import com.socialCircle.exception.ResourceNotFoundException;
import com.socialCircle.service.PostService;
@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostRepository postRepository;
	@Autowired
	private ModelMapper modelMapper;
	/*
	 * CRUD opertion for  Post resources
	 */

	@Override
	public PostDto getPostById(long postId) {
		Post post=postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "PostId", postId));
		PostDto postDto=postToPostDto(post);
		return postDto;
	}

	@Override
	public PostResponse getAllPosts(int pageNumber,int pageSize ,String sortBy) {
		Pageable pageable=PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
		
		Page<Post> pageOfPost = postRepository.findAll(pageable);
		List<Post> posts = pageOfPost.toList();
		List<PostDto> postDtos = posts.stream().map(post-> postToPostDto(post)).collect(Collectors.toList());
		
		PostResponse postResponse=new PostResponse();
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pageOfPost.getNumber());
		postResponse.setPageSize(pageOfPost.getSize());
		postResponse.setTotalElements(pageOfPost.getNumberOfElements());
		postResponse.setTotalPages(pageOfPost.getTotalPages());
		postResponse.setLast(pageOfPost.isLast());
		
		return postResponse;
	}

	@Override
	public PostDto createPost(PostDto postDto) {
		Post post = postDtoToPost(postDto);
		Post savedPost = postRepository.save(post);
		PostDto outputPostDto = postToPostDto(savedPost);
		return outputPostDto;
	}

	@Override
	public PostDto updatePost(PostDto postDto , long postId) {
		Post post = postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "PostId", postId));
		post.setContent(postDto.getContent());
		post.setDescription(postDto.getDescription());
		post.setTitle(postDto.getTitle());
		Post saveedPost = postRepository.save(post);
		PostDto outputPostDto = postToPostDto(saveedPost);
		return outputPostDto;
	}

	@Override
	public String deletePost(long postId) {
		Post post = postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "PostId", postId));
		postRepository.delete(post);
		return String.format("Post with id := %s deleted successfully", postId);
	}
	
	/*
	 * conversion of Post to postDto and postDto to Post
	 */
	
	private PostDto postToPostDto(Post post) {
		PostDto postDto = modelMapper.map(post, PostDto.class);
		return postDto;
		
	}
	
	private Post postDtoToPost(PostDto postDto) {
		Post post = modelMapper.map(postDto, Post.class);
		return post;

	}

}

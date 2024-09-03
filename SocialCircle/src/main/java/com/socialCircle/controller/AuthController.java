package com.socialCircle.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.socialCircle.entityDTO.AuthDto;
import com.socialCircle.entityDTO.RegisterDto;
import com.socialCircle.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	
	@Autowired
	private AuthService authService;

	@PostMapping(value = {"/login","/signIn"})
	public ResponseEntity<String> logIn(@RequestBody AuthDto authDto){
		String logIn = authService.logIn(authDto);
		return new ResponseEntity<String>(logIn,HttpStatus.OK);
	}
	@PostMapping(value = {"/signUp","/register"})
	public ResponseEntity<String> registerUser(@RequestBody RegisterDto registerDto){
		String user = authService.registerUser(registerDto);
		return new ResponseEntity<String>(user,HttpStatus.CREATED);
	}
	
	
}

package com.socialCircle.service;

import com.socialCircle.entityDTO.AuthDto;
import com.socialCircle.entityDTO.RegisterDto;


public interface AuthService {
	
	String logIn(AuthDto atAuthDto);
	
	String registerUser(RegisterDto registerDto);

}

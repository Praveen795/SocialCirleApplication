package com.socialCircle.serviceImpl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.socialCircle.Dao.RoleRepository;
import com.socialCircle.Dao.UserRepository;
import com.socialCircle.entity.Role;
import com.socialCircle.entity.User;
import com.socialCircle.entityDTO.AuthDto;
import com.socialCircle.entityDTO.RegisterDto;

import com.socialCircle.exception.SocialCircleException;
import com.socialCircle.securityConfig.JwtTokenUtil;
import com.socialCircle.service.AuthService;
@Service
public class AuthServiceImpl  implements AuthService{
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private JwtTokenUtil jwtatokenUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	public String logIn(AuthDto authDto) {
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authDto.getUserNameOrEmail(), authDto.getPassWord()));
		 SecurityContextHolder.getContext().setAuthentication(authentication);
		 return jwtatokenUtil.generateToken(authentication);
	}

	@Override
	public String registerUser(RegisterDto registerDto) {
		/*
		 * check email or username exist in database or not
		 */
		if(userRepository.existsByEmail(registerDto.getEmail()) && userRepository.existsByUserName(registerDto.getUserName())) {
			throw new SocialCircleException(HttpStatus.BAD_REQUEST, "email and userName already exist");
			
		}
		
		if(userRepository.existsByEmail(registerDto.getEmail())) {
			throw new SocialCircleException(HttpStatus.BAD_REQUEST, "email already exist");
		}
		if(userRepository.existsByUserName(registerDto.getUserName())) {
			throw new SocialCircleException(HttpStatus.BAD_REQUEST, "username already exist");
		}
		/*
		 * convert resisterDto to user
		 */
		
		User user=new User();
		user.setName(registerDto.getName());
		user.setEmail(registerDto.getEmail());
		user.setUserName(registerDto.getUserName());
		user.setPassWord(passwordEncoder.encode(registerDto.getPassWord()));
		Set<Role> roles=new HashSet<>();	
		Role role = roleRepository.findByRoleName("ROLE_USER").get();
		roles.add(role);
		
		userRepository.save(user);
		
		return "user registered successfully ";
	}

}

package com.socialCircle.securityConfig;

import java.util.Set;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.socialCircle.Dao.UserRepository;

import com.socialCircle.entity.User;
@Service
public class CustomUserDetailsService  implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
		User user = userRepository.findByUserNameOrEmail(usernameOrEmail, usernameOrEmail).orElseThrow(
				()->new UsernameNotFoundException("user not found ith this email or user name :"+usernameOrEmail));
		
		Set<SimpleGrantedAuthority> authority = user.getRoles().stream()
				.map((role) -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toSet());

		return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassWord(),authority);
	}

}

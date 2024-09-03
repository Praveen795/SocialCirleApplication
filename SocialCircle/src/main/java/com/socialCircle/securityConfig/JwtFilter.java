package com.socialCircle.securityConfig;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Component
public class JwtFilter extends OncePerRequestFilter{

	
	@Autowired 
	private JwtTokenUtil jwtTokenUtil;
	@Autowired
	private ApplicationContext context;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authHeader=request.getHeader("Authorization");
		String token=null;
		String userName=null;
		if(authHeader!=null && authHeader.startsWith("Bearer ")) {
			token=authHeader.substring(7);
			userName=jwtTokenUtil.extractUserName(token);
			
		}
		if(userName!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			UserDetails userDetails=context.getBean(CustomUserDetailsService.class).loadUserByUsername(userName);
			if(jwtTokenUtil.validateToken(token,userDetails)) {
				UsernamePasswordAuthenticationToken tokenOutput=new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
			
				tokenOutput.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(tokenOutput);
			}		
			
		}
		filterChain.doFilter(request, response);

		
		
	}

}
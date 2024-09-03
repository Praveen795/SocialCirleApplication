package com.socialCircle.securityConfig;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenUtil {

	
	private String secret="";

	/*
	 * @Value("${jwt.expiration}") private Duration expiration;
	 */
	public JwtTokenUtil() {
		try {
			KeyGenerator keyGen=KeyGenerator.getInstance("HmacSHA256");
			SecretKey skKey=keyGen.generateKey();
			secret=Base64.getEncoder().encodeToString(skKey.getEncoded());
		}catch (Exception e) {
			
			throw new RuntimeException();
		}
	}

	
	public String generateToken(Authentication authentication) {
		String userName = authentication.getName();
		Date currentDate = new Date();
		Date expirationDate = new Date(currentDate.getTime() + 60*60*30);

		Map<String,Object> claims=new HashMap<>();
		String token = Jwts.builder()
				.claims(claims)
				.subject(userName)
				.issuedAt(currentDate)
				.expiration(expirationDate)
				.signWith(key()).compact();
		return token;
	}

	private SecretKey key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }


	public String extractUserName(String token) {
	
		  return extractClaim(token, Claims::getSubject);
	}


	public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUserName(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
	
	 public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
	        final Claims claims = extractAllClaims(token);
	        return claimsResolver.apply(claims);
	    }
	
	
	 private Claims extractAllClaims(String token) {
	        return Jwts.parser()
	        		 .verifyWith(key())
	                 .build()
	                 .parseSignedClaims(token)
	                 .getPayload();
	     }
	 
	  private Boolean isTokenExpired(String token) {
	        return extractExpiration(token).before(new Date());
	    }
	  
	  public Date extractExpiration(String token) {
	        return extractClaim(token, Claims::getExpiration);
	    }
	
	
	   
}

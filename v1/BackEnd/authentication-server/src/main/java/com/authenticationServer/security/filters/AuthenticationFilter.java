package com.authenticationServer.security.filters;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.authenticationServer.model.entity.Users;
import com.authenticationServer.objects.LoginRequest;
import com.authenticationServer.service.UserService;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	@Autowired
	private UserService usersService;
	
	@Autowired
	private Environment environment;
	
	public AuthenticationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}
	
	public AuthenticationFilter(UserService usersService, Environment environment,
			AuthenticationManager authenticationManager) {
		super(authenticationManager);
		this.usersService = usersService;
		this.environment = environment;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) {
		try {

			LoginRequest creds = new ObjectMapper().readValue(req.getInputStream(), LoginRequest.class);

			return getAuthenticationManager().authenticate(
					new UsernamePasswordAuthenticationToken(creds.getUsername(), creds.getPassword(), new ArrayList<>()));

		} catch (IOException e) {
		    throw new RuntimeException(e);
		} catch (AuthenticationException e) {
		    res.setStatus(HttpStatus.UNAUTHORIZED.value());
		    return null;
		} catch (RuntimeException e) {
		    res.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value()); // send 500 Internal Server Error status
		    return null;
		}
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth) throws StreamWriteException, DatabindException, IOException {
		String username = ((User)auth.getPrincipal()).getUsername();
		Users userDetails = usersService.findByEmail(username);
		String tokenSecret = environment.getProperty("token.secret");
		byte[] secretKeyBytes = Base64.getEncoder().encode(tokenSecret.getBytes());
		SecretKey secretKey = new SecretKeySpec(secretKeyBytes, SignatureAlgorithm.HS512.getJcaName());
		
		Instant now = Instant.now();
		Date expirationDate = Date.from(now.plusSeconds(14400));
		
		String token = Jwts.builder().setSubject(userDetails.getUser_id().toString())
			.setExpiration(expirationDate)
			.setIssuedAt(Date.from(now))
			.signWith(secretKey, SignatureAlgorithm.HS512)
			.compact();
		
		// Create a JSON response body containing user details and token
		Map<String, Object> responseBody = new HashMap<>();
	    
		responseBody.put("id", userDetails.getUser_id().toString());
		responseBody.put("name", userDetails.getName());
		responseBody.put("token", token);
		responseBody.put("role", userDetails.getRole());
		responseBody.put("is_temp_password", userDetails.getIs_temp_password().toString());
		responseBody.put("expiration", expirationDate.toString());
	    
		// Set the response status and content type
		res.setStatus(HttpStatus.OK.value());
		res.setContentType(MediaType.APPLICATION_JSON_VALUE);
	    
		// Write the response body to the output stream
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.writeValue(res.getWriter(), responseBody);
	}
	
}
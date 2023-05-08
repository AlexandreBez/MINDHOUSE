package com.usersserver.security;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.usersserver.model.entity.Users;
import com.usersserver.objects.LoginRequest;
import com.usersserver.service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	
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

			User creds = new ObjectMapper().readValue(req.getInputStream(), User.class);

			return getAuthenticationManager().authenticate(
					new UsernamePasswordAuthenticationToken(creds.getUsername(), creds.getPassword(), new ArrayList<>()));

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth){
		String username = ((LoginRequest)auth.getPrincipal()).getUsername();
		Users userDetails = usersService.getUserDetailsByEmail(username);
		String tokenSecrect = environment.getProperty("token.secret");
		byte[] secretKeyBytes = Base64.getEncoder().encode(tokenSecrect.getBytes());
		SecretKey secretKey = new SecretKeySpec(secretKeyBytes, SignatureAlgorithm.HS512.getJcaName());
		
		Instant now = Instant.now();
		
		String token = Jwts.builder().setSubject(userDetails.getUser_id().toString())
		.setExpiration(Date.from(now.plusMillis(3600000)))
		.setIssuedAt(Date.from(now))
		.signWith(secretKey, SignatureAlgorithm.HS512)
		.compact();
		
		res.addHeader("token", token);
		res.addHeader("user_id", userDetails.getUser_id().toString());
	}

}

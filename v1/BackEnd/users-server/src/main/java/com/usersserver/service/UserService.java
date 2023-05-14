package com.usersserver.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.usersserver.model.UserJPA;
import com.usersserver.model.entity.Users;
import com.usersserver.objects.CustomResponse;
import com.usersserver.objects.LoginRequest;

@Service
public class UserService implements UserServiceHelper{

	@Autowired
	UserJPA userRepository;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Transactional
	public ResponseEntity<CustomResponse> createUser(Users userDetails) {
	    LocalDateTime now = LocalDateTime.now();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	    String formattedDateTime = now.format(formatter);
		try {
			
			CustomResponse responseMsg = new CustomResponse();
			
			if(userDetails == null) {
				responseMsg.setMessage("Data sent is empty");
				responseMsg.setStatusCode(502);
				responseMsg.setTimestamp(formattedDateTime);
				return new ResponseEntity<>(responseMsg, HttpStatus.BAD_GATEWAY);
			}
			
			userDetails.setPassword(bCryptPasswordEncoder.encode(userDetails.getPassword()));
			
			userRepository.save(userDetails);
			
			responseMsg.setMessage("User created with success");
			responseMsg.setStatusCode(201);
			responseMsg.setTimestamp(formattedDateTime);
			
			return new ResponseEntity<>(responseMsg, HttpStatus.CREATED);
			
		} catch (Exception e) {
			CustomResponse ExceptionResp = new CustomResponse();
			ExceptionResp.setMessage(e.getMessage());
			ExceptionResp.setStatusCode(500);
			ExceptionResp.setTimestamp(formattedDateTime);
			return new ResponseEntity<>(ExceptionResp, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		List<Users> user = userRepository.findByEmail(username);
		
		if (user.isEmpty()) throw new UsernameNotFoundException(username);
		
		LoginRequest loginRequest = new LoginRequest();
		loginRequest.setUsername(user.get(0).getEmail());
		loginRequest.setPassword(user.get(0).getPassword());
		
		return new User(loginRequest.getUsername(), loginRequest.getPassword(), true, true, true, true, new ArrayList<>());
	}
	
	public Users getUserDetailsByEmail(String email) {
		
			List<Users> userDetails = userRepository.findByEmail(email);
			
			if(userDetails.isEmpty()) throw new UsernameNotFoundException(email);

			return userDetails.get(0);
	}
	
}

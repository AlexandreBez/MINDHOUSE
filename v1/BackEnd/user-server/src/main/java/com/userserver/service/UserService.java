package com.userserver.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.userserver.model.UserJPA;
import com.userserver.model.entity.User;

@Service
public class UserService {

	@Autowired
	UserJPA userRepository;
	
	public ResponseEntity<User> getUserById(Integer id){
		try {
			Optional<User> obj = userRepository.findById(id);
			if(obj.isEmpty()) {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}
			
			return new ResponseEntity<>(obj.get(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

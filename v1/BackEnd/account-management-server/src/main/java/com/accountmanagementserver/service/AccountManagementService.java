package com.accountmanagementserver.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.accountmanagementserver.objects.User;

@Service
public class AccountManagementService {

	public ResponseEntity<User> dummy(){
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
	
}

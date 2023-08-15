package com.authenticationServer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.authenticationServer.objects.CustomResponse;
import com.authenticationServer.objects.TempPasswordRequest;
import com.authenticationServer.service.UserService;


@RestController
public class TempPasswordController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("TemporaryPassword")
	public ResponseEntity<CustomResponse> encode(@RequestBody TempPasswordRequest passwordRequest){
		return userService.changeTempPassword(passwordRequest);
	}
	
	
	
}

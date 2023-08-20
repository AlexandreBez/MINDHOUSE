package com.authenticationServer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.authenticationServer.model.entity.Users;
import com.authenticationServer.objects.CustomResponse;
import com.authenticationServer.service.UserService;

@RestController
@RequestMapping("UserTools/v1/")
public class UserToolsController {

	@Autowired
	UserService userService;
	
	@PostMapping("saveUser")
	public ResponseEntity<CustomResponse> saveUser(@RequestBody Users users) {
		return userService.saveUser(users);
	}

}

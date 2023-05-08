package com.usersserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usersserver.model.entity.Users;
import com.usersserver.objects.CustomResponse;
import com.usersserver.service.UserService;

@RestController
@RequestMapping("/users-server/v1/")
public class UsersController {
	
	@Autowired
	UserService userService;

	@PostMapping("createNewUser")
	public ResponseEntity<CustomResponse> createUser(@RequestBody Users userDetails) {
		return userService.createUser(userDetails);
	}
	
}

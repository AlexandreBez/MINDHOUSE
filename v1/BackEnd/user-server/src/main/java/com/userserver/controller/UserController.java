package com.userserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.userserver.model.entity.User;
import com.userserver.service.UserService;

@RestController
@RequestMapping("user/v1/")
public class UserController {

	@Autowired
	UserService userService;
	
	@GetMapping("{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") Integer id){
		return userService.getUserById(id);
	}
}

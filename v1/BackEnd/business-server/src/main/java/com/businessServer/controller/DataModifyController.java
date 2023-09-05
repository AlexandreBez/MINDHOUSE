package com.businessServer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.businessServer.model.entity.UsersData;
import com.businessServer.objects.CustomResponse;
import com.businessServer.service.UserDataModifyService;

@RestController
@RequestMapping("DataModify/v1/")
public class DataModifyController {

	@Autowired
	UserDataModifyService userDataModifyService;
	
	@PostMapping("saveUserRegister")
	public ResponseEntity<CustomResponse> saveUser(@RequestBody UsersData userData){
		return userDataModifyService.saveUser(userData);
	}
	
	@PutMapping("updateUserRegister")
	public ResponseEntity<CustomResponse> updateUser(@RequestBody UsersData userData){
		return userDataModifyService.updateUser(userData);
	}
	
	@PutMapping("dissableUserRegister")
	public ResponseEntity<CustomResponse> dissableUser(@RequestBody UsersData userData){
		return userDataModifyService.dissableUser(userData);
	}
	
}

package com.businessServer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.businessServer.model.entity.Users;
import com.businessServer.objects.CustomResponse;
import com.businessServer.service.UserDataModifyService;

@RestController
@RequestMapping("/DataModify/v1/")
public class UserDataModifyController {

	@Autowired
	UserDataModifyService userDataModifyService;
	
	@PostMapping("ModifyUser")
	public ResponseEntity<CustomResponse> modifyUserInformations(@RequestBody Users users){
		return userDataModifyService.modifyUserInformations(users);
	}
}

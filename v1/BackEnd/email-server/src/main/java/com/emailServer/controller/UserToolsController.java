package com.emailServer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emailServer.objects.CustomResponse;
import com.emailServer.service.UserToolsService;

@RestController
@RequestMapping("UserTools/")
public class UserToolsController {

	@Autowired
	UserToolsService userToolsService;
	
	@GetMapping("SendTokenByEmail/{email}/{token}")
	public ResponseEntity<CustomResponse> changeTempPassword(@PathVariable("email") String email,@PathVariable("token") String token){
		return userToolsService.sendEmailWithToken(email, token);
	}
	
}

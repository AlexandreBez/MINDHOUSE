package com.authenticationServer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.authenticationServer.objects.CustomResponse;
import com.authenticationServer.objects.ResetPasswordRequest;
import com.authenticationServer.service.UserService;

@RestController
@RequestMapping("ResetPasswordTools/")
public class ResetPasswordToolsController {

	@Autowired
	UserService userService;

	@GetMapping("SendToken/{email}")
	public ResponseEntity<CustomResponse> sendToken(@PathVariable("email") String email) {
		return userService.sendToken(email);
	}
	
	@GetMapping("ValidateToken/{email}/{token}")
	public ResponseEntity<CustomResponse> validateToken(@PathVariable("email") String email, @PathVariable("token") String token) {
		return userService.validateToken(email, token);
	}
	
	@GetMapping("ResendToken/{email}")
	public ResponseEntity<CustomResponse> resendToken(@PathVariable("email") String email) {
		return userService.resendToken(email);
	}
	
	@PostMapping("ResetPassword")
	public ResponseEntity<CustomResponse> resetPassword(@RequestBody ResetPasswordRequest passwordRequest) {
		return userService.resetPassword(passwordRequest);
	}
	
}

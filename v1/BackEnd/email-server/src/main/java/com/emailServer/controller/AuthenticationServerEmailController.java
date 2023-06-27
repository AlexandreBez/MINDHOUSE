package com.emailServer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.emailServer.objects.CustomResponse;
import com.emailServer.objects.TokenExchange;
import com.emailServer.service.AuthenticationEmailService;

@RestController
@RequestMapping(value="login-tools/v1/", method = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.POST, RequestMethod.PUT})
public class AuthenticationServerEmailController {
	
	@Autowired
	AuthenticationEmailService authenticationEmailService;

	@GetMapping("sendTokenForEmail")
	public ResponseEntity<CustomResponse> sendTokenForEmail(@RequestBody TokenExchange tokenExchange){
		return authenticationEmailService.sendTokenForEmail(tokenExchange);
	}
}

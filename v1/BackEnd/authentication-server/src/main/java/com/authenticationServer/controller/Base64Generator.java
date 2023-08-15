package com.authenticationServer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Base64Generator {
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@GetMapping("encode/{text}")
	public String encode(@PathVariable("text") String text){
		return bCryptPasswordEncoder.encode(text);
	}
	
	@GetMapping("upgradeEncoding/{text}")
	public boolean upgradeEncoding(@PathVariable("text") String text){
		return bCryptPasswordEncoder.upgradeEncoding(text);
	}
	
}
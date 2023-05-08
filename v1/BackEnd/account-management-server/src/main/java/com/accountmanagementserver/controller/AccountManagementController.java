package com.accountmanagementserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accountmanagementserver.objects.User;
import com.accountmanagementserver.service.AccountManagementService;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("account-management/v1/")
public class AccountManagementController {
	
	@Autowired
	AccountManagementService accountManagementService;

	@GetMapping("dummy")
	public ResponseEntity<User> dummy(){
		return accountManagementService.dummy();
	}
}

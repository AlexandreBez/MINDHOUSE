package com.authenticationServer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.authenticationServer.model.entity.Users;
import com.authenticationServer.objects.CustomResponse;
import com.authenticationServer.objects.UserTableResponse;
import com.authenticationServer.service.UserService;

@RestController
@RequestMapping("UserTools/v1/")
public class UserToolsController {

	@Autowired
	UserService userService;
	
	@PostMapping("saveUser")
	public ResponseEntity<CustomResponse> saveUser(@RequestBody Users user) {
		return userService.saveUser(user);
	}
	
	@GetMapping("getUsersTableData")
	public ResponseEntity<List<UserTableResponse>> getUsersTableData(){
		return userService.getUsersTableData();
	}
	
	@GetMapping("filterSearch/{type}/{data}")
	public ResponseEntity<List<UserTableResponse>> filterSearch(@PathVariable("type") String type, @PathVariable("data") String data){
		return userService.filterSearch(type,data);
	}

	@DeleteMapping("deleteUser/{id}")
	public ResponseEntity<CustomResponse> deleteUser(@PathVariable("id") Integer id) {
		return userService.deleteUser(id);
	}
	
	@PutMapping("updateUser")
	public ResponseEntity<CustomResponse> updateUser(@RequestBody Users user) {
		return userService.updateUser(user);
	}
}

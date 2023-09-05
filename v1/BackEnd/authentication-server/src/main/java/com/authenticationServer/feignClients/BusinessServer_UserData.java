package com.authenticationServer.feignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.authenticationServer.objects.CustomResponse;
import com.authenticationServer.objects.UsersData;

@Component
@FeignClient(name="business-server", path="DataModify/v1/")
public interface BusinessServer_UserData{

	@PostMapping("saveUserRegister")
	public ResponseEntity<CustomResponse> saveUser(@RequestBody UsersData userData);
	
	@PutMapping("updateUserRegister")
	public ResponseEntity<CustomResponse> updateUser(@RequestBody UsersData userData);
	
	@PutMapping("dissableUserRegister")
	public ResponseEntity<CustomResponse> dissableUser(@RequestBody UsersData userData);
}
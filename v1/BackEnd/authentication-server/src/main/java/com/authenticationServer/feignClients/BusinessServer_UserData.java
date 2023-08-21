package com.authenticationServer.feignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.authenticationServer.objects.CustomResponse;
import com.authenticationServer.objects.UsersBusinessServer;

@Component
@FeignClient(name="business-server", path="/DataModify/v1/")
public interface BusinessServer_UserData{

	@PostMapping("ModifyUser")
	public ResponseEntity<CustomResponse> modifyUserInformations(@RequestBody UsersBusinessServer usersBusinessServer);

}
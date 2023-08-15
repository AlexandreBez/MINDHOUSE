package com.authenticationServer.feignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.authenticationServer.objects.CustomResponse;

/**
 * The Interface EmailServerClient.
 */
@Component
@FeignClient(name="email-server", path="UserTools/")
public interface EmailServer_UserToolsClient {

	@PostMapping("SendTokenByEmail/{email}/{token}")
	public ResponseEntity<CustomResponse> changeTempPassword(@PathVariable("email") String email,@PathVariable("token") String token);

}

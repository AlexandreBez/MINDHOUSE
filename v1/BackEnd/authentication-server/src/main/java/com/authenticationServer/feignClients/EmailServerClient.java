package com.authenticationServer.feignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.authenticationServer.objects.CustomResponse;
import com.authenticationServer.objects.TokenExchange;

@Component
@FeignClient(name="email-server", path="login-tools/v1/")
public interface EmailServerClient {

    /**
     * Sends user information Excel data to the email server.
     *
     * @param fileShareHelper The FileShareHelper containing the data to be sent.
     * @return The ResponseEntity containing the response from the email server.
     */
	@GetMapping("sendTokenForEmail")
	ResponseEntity<CustomResponse> sendTokenForEmail(@RequestBody TokenExchange tokenExchange);
	
}

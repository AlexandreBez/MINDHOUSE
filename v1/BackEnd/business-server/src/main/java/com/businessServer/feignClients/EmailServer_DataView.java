package com.businessServer.feignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.businessServer.objects.CustomResponse;
import com.businessServer.objects.FileShareHelper;

@Component
@FeignClient(name = "email-server", path = "ReportTools/")
public interface EmailServer_DataView {

	@GetMapping("sendUserInfoExcelData")
	ResponseEntity<CustomResponse> sendUserInfoExcelData(@RequestBody FileShareHelper fileShareHelper);
	
}

package com.emailServer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.emailServer.objects.CustomResponse;
import com.emailServer.objects.FileShareHelper;
import com.emailServer.service.BusinessIntelligenceEmailService;

@RestController
@RequestMapping(value="admin-workspace/v1/", method = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.POST, RequestMethod.PUT})
public class BusinessIntelligenceEmailController {

	@Autowired
	BusinessIntelligenceEmailService adminWorkspaceBIService;
	
	@GetMapping("usersFileInfo")
	public ResponseEntity<CustomResponse> sendUserInfoExcelData(@RequestBody FileShareHelper fileShareHelper){
		return adminWorkspaceBIService.sendUserInfoExcelData(fileShareHelper);
	}
}

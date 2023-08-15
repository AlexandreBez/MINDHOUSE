package com.emailServer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emailServer.objects.CustomResponse;
import com.emailServer.objects.FileShareHelper;
import com.emailServer.service.ReportToolsService;

@RestController
@RequestMapping("ReportTools/")
public class ReportToolsController {
	
	@Autowired
	ReportToolsService reportToolsService;
	
	@PostMapping("sendUserInfoExcelData")
	ResponseEntity<CustomResponse> sendUserInfoExcelData(@RequestBody FileShareHelper fileShareHelper){
		return reportToolsService.sendUserInfoExcelData(fileShareHelper);
	}
}

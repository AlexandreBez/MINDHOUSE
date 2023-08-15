package com.businessServer.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.businessServer.objects.AmountOfRolesGraphicData;
import com.businessServer.objects.CustomResponse;
import com.businessServer.objects.ListOfEmails;
import com.businessServer.service.UserDataViewService;

@RestController
@RequestMapping("/DataView/v1/")
public class DataViewController {

	@Autowired
	UserDataViewService userDataViewService;

	@GetMapping("getAmountOfRolesGraphicData")
	public ResponseEntity<List<AmountOfRolesGraphicData>> getAmountOfRolesGraphicData() {
		return userDataViewService.getAmountOfRolesGraphicData();
	}
	
	@GetMapping("getListOfEmails")
	public ResponseEntity<Optional<String[]>> getListOfEmails() {
		return userDataViewService.getListOfEmails();
	}

	@GetMapping("downloadExcelUserReport")
    public ResponseEntity<ByteArrayResource> downloadExcelUserReport() {
        try {
            return userDataViewService.downloadExcelUserReport();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
	
    @PostMapping("sendExcelUserReportForEmails")
    public ResponseEntity<CustomResponse> sendExcelUserReportForEmails(@RequestBody String[] emails) {
        return userDataViewService.sendExcelUserReportForEmails(emails);
    }

}

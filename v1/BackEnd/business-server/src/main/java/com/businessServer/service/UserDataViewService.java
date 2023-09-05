package com.businessServer.service;

import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tomcat.util.file.ConfigurationSource.Resource;
import org.apache.poi.ss.usermodel.*;

import org.springframework.stereotype.Service;

import com.businessServer.feignClients.EmailServer_DataView;
import com.businessServer.model.UserJPA;
import com.businessServer.model.entity.UsersData;
import com.businessServer.objects.AmountOfRolesGraphicData;
import com.businessServer.objects.CustomResponse;
import com.businessServer.objects.FileShareHelper;
import com.businessServer.objects.ListOfEmails;
import com.businessServer.objects.UserReportData;
import com.businessServer.userReportFactory.UserExcelReport;

import java.io.IOException;
import java.io.OutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@Service
public class UserDataViewService {

	@Autowired
	UserJPA userJPA;
	
	@Autowired
	UserExcelReport userExcelReport;

	@Autowired
	EmailServer_DataView emailServer_DataView;

	@Transactional
	public ResponseEntity<List<AmountOfRolesGraphicData>> getAmountOfRolesGraphicData() {
		try {
			
			List<Object[]> result = userJPA.getUserQtdRolesData();

			if (result.isEmpty()) return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

			List<AmountOfRolesGraphicData> userQtdRoleDataList = new ArrayList<>();
			for (Object[] row : result) {
				
				String role = (String) row[0];
				BigInteger qtd = (BigInteger) row[1];
				
				AmountOfRolesGraphicData userQtdRoleData = new AmountOfRolesGraphicData(role, qtd);
				
				userQtdRoleDataList.add(userQtdRoleData);
			}

			return new ResponseEntity<>(userQtdRoleDataList, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Transactional
	public ResponseEntity<Optional<String[]>> getListOfEmails() {
		try {
			
			Optional<String[]> result = userJPA.getListOfEmails();
			
			if (result.isEmpty()) {
				return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
			}

			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<ByteArrayResource> downloadExcelUserReport(){

		try {
			
			List<UsersData> result = userJPA.findAll();

			if (result.isEmpty()) {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}

			ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(userExcelReport.createUserExcelWorkbook(result)));

	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
	        headers.setContentDispositionFormData("attachment", "user_report.xlsx");

	        return ResponseEntity.ok()
	                .headers(headers)
	                .contentLength(resource.contentLength())
	                .body(resource);

	    } catch (Exception e) {
	        System.out.println(e.getMessage());
	        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

	@Transactional
	public ResponseEntity<CustomResponse> sendExcelUserReportForEmails(String[] emails) {
		
		CustomResponse response = new CustomResponse();
		try {
			
			List<UsersData> result = userJPA.findAll();

			if (result.isEmpty()) {
				response.setMessage("There is no data to generate a report");
				response.setStatusCode(404);
				response.setTimestamp(new Date().toString());
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}
			
			if (emails == null) {
				response.setMessage("Data sent is empty...");
				response.setStatusCode(404);
				response.setTimestamp(new Date().toString());
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}
			
			String filePath = userExcelReport.createUserExcelWorkbookAndSave(result).toString();
			
			FileShareHelper fileShareHelper = new FileShareHelper(emails, "user_report.xlsx", filePath);
			
			response = emailServer_DataView.sendUserInfoExcelData(fileShareHelper).getBody();
			
			if (response.getStatusCode() != 200) {
				response.setMessage("Data sent is empty...");
				response.setStatusCode(404);
				response.setTimestamp(new Date().toString());
				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			}
			
			response.setMessage("Email sent with success...");
			response.setStatusCode(200);
			response.setTimestamp(new Date().toString());
			return new ResponseEntity<>(response, HttpStatus.OK);
		
		} catch (Exception e) {
			response.setMessage(e.getMessage());
			response.setStatusCode(500);
			response.setTimestamp(new Date().toString());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}

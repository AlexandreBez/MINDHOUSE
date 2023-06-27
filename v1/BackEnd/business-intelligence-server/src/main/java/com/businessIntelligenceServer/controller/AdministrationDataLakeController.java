package com.businessIntelligenceServer.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.businessIntelligenceServer.objects.CustomResponse;
import com.businessIntelligenceServer.objects.UsersQtdRolesData;
import com.businessIntelligenceServer.service.AdministrationDataLakeService;

/**
 * Controller class for handling data-related API endpoints.
 * Handles data requests operations for managing graphics.
 *
 * @author Lucas Alexandre
 * @version 1.0.0
 */
@RestController
@RequestMapping("/administration-workstation/Business-Inteligence/v1/")
public class AdministrationDataLakeController {
	
	@Autowired
	AdministrationDataLakeService administrationDataLakeService;
	
	/**
	 * Endpoint for get the User Quantity based on the role.
	 *
	 * @return ResponseEntity containing a List of UsersQtdRolesData Obejct.
	 */
	@GetMapping("getUserQtdData")
	public ResponseEntity<List<UsersQtdRolesData>> getUserQtdRolesData(){
		return administrationDataLakeService.getUserQtdRolesData();
	}
	
	/**
	 * Endpoint for creating a new user.
	 * Accepts a JSON payload containing user details and creates a new user.
	 *
	 * @param id The user id as a URL parameter.
	 * @return ResponseEntity containing a CustomResponse indicating the status of the operation.
	 * @throws IOException if an error occurs during the file creation process.
	 */
	@GetMapping("sendUserQtdRolesDataFileByEmail/{id}")
	public ResponseEntity<CustomResponse> generateRoleQtdUsersExcelFile(@PathVariable("id") Integer id) throws IOException{
		return administrationDataLakeService.generateRoleQtdUsersExcelFile(id);
	}
	
}


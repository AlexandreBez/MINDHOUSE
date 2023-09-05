package com.businessServer.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.businessServer.model.UserJPA;
import com.businessServer.model.entity.UsersData;
import com.businessServer.objects.CustomResponse;

@Service
public class UserDataModifyService {

	@Autowired
	UserJPA userRepository;

	@Transactional
	public ResponseEntity<CustomResponse> saveUser(UsersData user) {
		CustomResponse response = new CustomResponse();
		try {

			if (user == null) {
				response.setMessage("Data sent is empty...");
				response.setStatusCode(400);
				response.setTimestamp(new Date().toString());
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			}
			
			List<UsersData> checkUserExist = userRepository.findByEmail(user.getEmail());
			
			if (!checkUserExist.isEmpty()) {
				checkUserExist.get(0).setName(user.getName());
				checkUserExist.get(0).setEmail(user.getEmail());
				checkUserExist.get(0).setRole(user.getRole());
				checkUserExist.get(0).setStatus(user.getStatus());
				checkUserExist.get(0).setCreated_on(user.getCreated_on());
				checkUserExist.get(0).setUpdated_on(null);
				userRepository.save(checkUserExist.get(0));
				
				response.setMessage("Register added with success");
				response.setStatusCode(200);
				response.setTimestamp(new Date().toString());
				return new ResponseEntity<>(response, HttpStatus.OK);
				
			}else {
				userRepository.save(user);
				response.setMessage("Register added with success");
				response.setStatusCode(200);
				response.setTimestamp(new Date().toString());
				return new ResponseEntity<>(response, HttpStatus.OK);
			}

		} catch (Exception e) {
			response.setMessage(e.getMessage());
			response.setStatusCode(500);
			response.setTimestamp(new Date().toString());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Transactional
	public ResponseEntity<CustomResponse> updateUser(UsersData user) {
		CustomResponse response = new CustomResponse();
		try {

			if (user == null) {
				response.setMessage("Data sent is empty...");
				response.setStatusCode(400);
				response.setTimestamp(new Date().toString());
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			}
			
			List<UsersData> checkUserExist = userRepository.findByEmail(user.getEmail());
			
			if (checkUserExist.isEmpty()) {
				
				user.setStatus("ENABLED");
				user.setCreated_on(user.getUpdated_on());
				userRepository.save(user);
				
				response.setMessage("Register modfied with success");
				response.setStatusCode(200);
				response.setTimestamp(new Date().toString());
				return new ResponseEntity<>(response, HttpStatus.OK);
			}
			
			checkUserExist.get(0).setName(user.getName());
			checkUserExist.get(0).setEmail(user.getEmail());
			checkUserExist.get(0).setRole(user.getRole());
			checkUserExist.get(0).setStatus("ENABLED");
			checkUserExist.get(0).setUpdated_on(user.getUpdated_on());
			userRepository.save(checkUserExist.get(0));
			
			response.setMessage("Register modfied with success");
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
	
	@Transactional
	public ResponseEntity<CustomResponse> dissableUser(UsersData user) {
		CustomResponse response = new CustomResponse();
		try {

			if (user == null) {
				response.setMessage("Data sent is empty...");
				response.setStatusCode(400);
				response.setTimestamp(new Date().toString());
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			}
			
			List<UsersData> checkUserExist = userRepository.findByEmail(user.getEmail());
			
			if (checkUserExist.isEmpty()) {
				
				response.setMessage("Register don't exist in Database");
				response.setStatusCode(404);
				response.setTimestamp(new Date().toString());
				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
				
			}
			
			checkUserExist.get(0).setStatus(user.getStatus());
			checkUserExist.get(0).setUpdated_on(user.getUpdated_on());
			userRepository.save(checkUserExist.get(0));
			
			response.setMessage("Register modfied with success");
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

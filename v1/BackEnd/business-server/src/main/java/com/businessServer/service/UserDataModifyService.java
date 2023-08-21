package com.businessServer.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.businessServer.model.UserJPA;
import com.businessServer.model.entity.Users;
import com.businessServer.objects.CustomResponse;


@Service
public class UserDataModifyService {

	@Autowired
	UserJPA userRepository;

	public ResponseEntity<CustomResponse> modifyUserInformations(Users user) {
		CustomResponse response = new CustomResponse();
	    try {
	    	
	    	if (user == null) {
		    	response.setMessage("Data sent is empty...");
		    	response.setStatusCode(400);
				response.setTimestamp(new Date().toString());
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			}
	    	
	    	List<Users> checkUser = userRepository.findByEmail(user.getEmail());
	    	
	    	if (checkUser.isEmpty()) {
	    		userRepository.save(user);
		    	response.setMessage("User modfied/added with success...");
		    	response.setStatusCode(200);
				response.setTimestamp(new Date().toString());
		        return new ResponseEntity<>(response, HttpStatus.OK);
			}
	    	
	    	user.setUser_id(checkUser.get(0).getUser_id());
	        userRepository.save(user);
	        
	    	response.setMessage("User modfied/added with success...");
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

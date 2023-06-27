package com.authenticationServer.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.authenticationServer.feignClients.EmailServerClient;
import com.authenticationServer.model.UserJPA;
import com.authenticationServer.model.entity.Users;
import com.authenticationServer.objects.CustomResponse;
import com.authenticationServer.objects.FilterSearch;
import com.authenticationServer.objects.LoginRequest;

/**
 * Service class for user-related operations.
 */
@Service
public class UserService implements UserServiceHelper {

	@Autowired
	UserJPA userRepository;
	
	@Autowired
	RabbitTemplate rabbitTemplate;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	EmailServerClient emailServerClient;
	
	/**
	 * Creates a new user.
	 *
	 * @param user the user object to create
	 * @return the ResponseEntity containing the response message and status code
	 * @throws IOException if an I/O error occurs
	 */
	@Transactional
	public ResponseEntity<CustomResponse> createUser(Users user) throws IOException {
	    LocalDateTime now = LocalDateTime.now();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	    String formattedDateTime = now.format(formatter);
		try {
			
			CustomResponse responseMsg = new CustomResponse();
			
			if(user == null) {
				responseMsg.setMessage("Data sent is empty");
				responseMsg.setStatusCode(400);
				responseMsg.setTimestamp(formattedDateTime);
				return new ResponseEntity<>(responseMsg, HttpStatus.BAD_REQUEST);
			} else if(!userRepository.findByEmail(user.getEmail()).isEmpty()) {
				responseMsg.setMessage("Email already in use...");
				responseMsg.setStatusCode(400);
				responseMsg.setTimestamp(formattedDateTime);
				return new ResponseEntity<>(responseMsg, HttpStatus.BAD_REQUEST);
			}
			
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			user.setCreation_date(formattedDateTime);
			
			try {
				rabbitTemplate.convertAndSend("Data_to_business_server", "user.add.bi", user);
			} catch (Exception e) {
				responseMsg.setMessage(e.getMessage());
				responseMsg.setStatusCode(500);
				responseMsg.setTimestamp(formattedDateTime);
				return new ResponseEntity<>(responseMsg, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			userRepository.save(user);
			
			
			responseMsg.setMessage("User created with success");
			responseMsg.setStatusCode(201);
			responseMsg.setTimestamp(formattedDateTime);
			
			return new ResponseEntity<>(responseMsg, HttpStatus.CREATED);
			
		} catch (Exception e) {
			CustomResponse ExceptionResp = new CustomResponse();
			ExceptionResp.setMessage(e.getMessage());
			ExceptionResp.setStatusCode(500);
			ExceptionResp.setTimestamp(formattedDateTime);
			return new ResponseEntity<>(ExceptionResp, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		List<Users> user = userRepository.findByEmail(username);
		
	    if (user.isEmpty()) {
	        throw new UsernameNotFoundException("User not found");
	    }
	    
		LoginRequest loginRequest = new LoginRequest();
		loginRequest.setUsername(user.get(0).getEmail());
		loginRequest.setPassword(user.get(0).getPassword());
		
		return new User(loginRequest.getUsername(), loginRequest.getPassword(), true, true, true, true, new ArrayList<>());
	}
	
	/**
	 * Retrieves user details by email.
	 *
	 * @param email the email of the user
	 * @return the user details
	 * @throws UsernameNotFoundException if the user is not found
	 */
	public Users getUserDetailsByEmail(String email) {
		
		List<Users> userDetails = userRepository.findByEmail(email);
		
		if(userDetails.isEmpty()) throw new UsernameNotFoundException(email);

		return userDetails.get(0);
	}
	
	/**
	 * Retrieves all users.
	 *
	 * @return the ResponseEntity containing the list of users or an error status
	 */
	@Transactional
    public ResponseEntity<List<Users>> getAllUsers() {
        try {
        	List<Users> dataResult = userRepository.findAll();
        	
        	if (dataResult.isEmpty()) {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}
        	
        	return new ResponseEntity<>(dataResult, HttpStatus.OK);
        } catch (Exception e) {
        	System.out.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	/**
	 * Searches for users based on a filter.
	 *
	 * @param filterSearch the filter search object containing the filter type and data
	 * @return the ResponseEntity containing the list of users or an error status
	 */
	@Transactional
    public ResponseEntity<List<Users>> searchUserByFilter(FilterSearch filterSearch) {
        try {
        	
        	List<Object[]> dataResult;
        	List<Users> userInfoDataList = new ArrayList<>();
        	
        	switch (filterSearch.getType().toString()) {
			case "name":
				dataResult = userRepository.searchUserByFilterName(filterSearch.getData());				
	        	if(dataResult.isEmpty()) {
	        		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	        	}
				for (Object[] data : dataResult) {
					
					Integer user_id = (Integer) data[0];
					String name = (String) data[1];
					String email = (String) data[2];
					String role = (String) data[3];
					
					Users newUserInfoData = new Users(user_id, name, email, role);
					
					userInfoDataList.add(newUserInfoData);
				}
				return new ResponseEntity<>(userInfoDataList, HttpStatus.OK);
			case "email":
				dataResult = userRepository.searchUserByFilterEmail(filterSearch.getData());
	        	if(dataResult.isEmpty()) {
	        		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	        	}
				for (Object[] data : dataResult) {
					
					Integer user_id = (Integer) data[0];
					String name = (String) data[1];
					String email = (String) data[2];
					String role = (String) data[3];
					
					Users newUserInfoData = new Users(user_id, name, email, role);
					
					userInfoDataList.add(newUserInfoData);
				}
				return new ResponseEntity<>(userInfoDataList, HttpStatus.OK);
			case "role":
				dataResult = userRepository.searchUserByFilterRole(filterSearch.getData());
	        	if(dataResult.isEmpty()) {
	        		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	        	}
				for (Object[] data : dataResult) {
					
					Integer user_id = (Integer) data[0];
					String name = (String) data[1];
					String email = (String) data[2];
					String role = (String) data[3];
					
					Users newUserInfoData = new Users(user_id, name, email, role);
					
					userInfoDataList.add(newUserInfoData);
				}
				return new ResponseEntity<>(userInfoDataList, HttpStatus.OK);
			default:
	            return getAllUsers();
        	}
        	
        } catch (Exception e) {
        	System.out.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	/**
	 * Retrieves a user by ID.
	 *
	 * @param id the ID of the user
	 * @return the ResponseEntity containing the user or an error status
	 */
	@Transactional
	public ResponseEntity<Optional<Users>> getUserById(Integer id) {
        try {
        	
        	Optional<Users> dataResult = userRepository.findById(id);
        	
        	if(dataResult.isEmpty()) {
        		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        	}
        	
        	return new ResponseEntity<>(dataResult, HttpStatus.OK);
        } catch (Exception e) {
        	System.out.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	/**
	 * Updates a user by ID.
	 *
	 * @param id the ID of the user
	 * @param users the updated user object
	 * @return the ResponseEntity containing the update response or an error status
	 */
	@Transactional
	public ResponseEntity<CustomResponse> updateUser(Integer id, Users users) {
		
	    LocalDateTime now = LocalDateTime.now();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	    String formattedDateTime = now.format(formatter);
		try {

			CustomResponse customResponse = new CustomResponse();
			
			Optional<Users> data = userRepository.findById(id);

			if (data.isEmpty()) {
				customResponse.setMessage("User not found...");
				customResponse.setStatusCode(404);
				customResponse.setTimestamp(formattedDateTime);
				return new ResponseEntity<>(customResponse, HttpStatus.NOT_FOUND);
			}else if(!userRepository.findByEmail(users.getEmail()).isEmpty()) {
				customResponse.setMessage("Email already in use...");
				customResponse.setStatusCode(400);
				customResponse.setTimestamp(formattedDateTime);
				return new ResponseEntity<>(customResponse, HttpStatus.BAD_REQUEST);
			}
			
			Users userForUpdate = data.get();

			if(!users.getPassword().isBlank() || users.getPassword() == null) {
				userForUpdate.setPassword(bCryptPasswordEncoder.encode(users.getPassword()));
			}

			userForUpdate.setName(users.getName());
			userForUpdate.setEmail(users.getEmail());
			userForUpdate.setRole(users.getRole());
			
			
			try {
				rabbitTemplate.convertAndSend("Data_to_business_server", "user.update.bi", userForUpdate);
			} catch (Exception e) {
				customResponse.setMessage(e.getMessage());
				customResponse.setStatusCode(500);
				customResponse.setTimestamp(formattedDateTime);
				return new ResponseEntity<>(customResponse, HttpStatus.INTERNAL_SERVER_ERROR);
			}

			userRepository.save(userForUpdate);
			
			customResponse.setMessage("User updated with success");
			customResponse.setStatusCode(200);
			customResponse.setTimestamp(formattedDateTime);
			
			return new ResponseEntity<>(customResponse, HttpStatus.OK);

		} catch (Exception e) {
			CustomResponse ExceptionResponse = new CustomResponse();
			ExceptionResponse.setMessage(e.getMessage());
			ExceptionResponse.setStatusCode(500);
			ExceptionResponse.setTimestamp(formattedDateTime);
			return new ResponseEntity<>(ExceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * Deletes a user by ID.
	 *
	 * @param id the ID of the user
	 * @return the ResponseEntity containing the delete response or an error status
	 */
	public ResponseEntity<CustomResponse> deleteUser(Integer id){
	    LocalDateTime now = LocalDateTime.now();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	    String formattedDateTime = now.format(formatter);
		try {
			
			CustomResponse customResponse = new CustomResponse();

			if (userRepository.findById(id).isEmpty()) {
				customResponse.setMessage("User not found");
				customResponse.setStatusCode(404);
				customResponse.setTimestamp(formattedDateTime);
				return new ResponseEntity<>(customResponse, HttpStatus.NOT_FOUND);
			}

			try {
				rabbitTemplate.convertAndSend("Data_to_business_server", "user.delete.bi", id);
			} catch (Exception e) {
				customResponse.setMessage(e.getMessage());
				customResponse.setStatusCode(500);
				customResponse.setTimestamp(formattedDateTime);
				return new ResponseEntity<>(customResponse, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			userRepository.deleteById(id);

			customResponse.setMessage("User deleted with success");
			customResponse.setStatusCode(200);
			customResponse.setTimestamp(formattedDateTime);
			
			return new ResponseEntity<>(customResponse, HttpStatus.OK);

		} catch (Exception e) {
			CustomResponse ExceptionResponse = new CustomResponse();
			ExceptionResponse.setMessage(e.getMessage());
			ExceptionResponse.setStatusCode(500);
			ExceptionResponse.setTimestamp(formattedDateTime);
			return new ResponseEntity<>(ExceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}


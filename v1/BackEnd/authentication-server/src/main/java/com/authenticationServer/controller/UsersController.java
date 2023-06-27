package com.authenticationServer.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.authenticationServer.model.entity.Users;
import com.authenticationServer.objects.CustomResponse;
import com.authenticationServer.objects.FilterSearch;
import com.authenticationServer.service.UserService;

/**
 * Controller class for handling user-related API endpoints.
 * Handles CRUD operations for managing users.
 * Provides endpoints for creating, retrieving, updating, and deleting users.
 *
 * @author Lucas Alexandre
 * @version 1.0.0
 */
@RestController
@RequestMapping("/administration-workstation/users/v1/")
public class UsersController {
	
	@Autowired
	UserService userService;
	

	/**
	 * Endpoint for creating a new user.
	 * Accepts a JSON payload containing user details and creates a new user.
	 *
	 * @param userDetails The user details as a JSON payload.
	 * @return ResponseEntity containing a CustomResponse indicating the status of the operation.
	 * @throws IOException if an error occurs during the user creation process.
	 */
	@PostMapping("createNewUser")
	public ResponseEntity<CustomResponse> createUser(@RequestBody Users userDetails) throws IOException {
		return userService.createUser(userDetails);
	}
	
    /**
     * Endpoint for retrieving all users.
     * Returns a list of all users.
     *
     * @return ResponseEntity containing a list of Users.
     */
    @GetMapping("getAllUsers")
    public ResponseEntity<List<Users>> getAllUsers() {
        return userService.getAllUsers();
    }
    
    /**
     * Endpoint for searching users based on a filter.
     * Accepts a FilterSearch object containing filter criteria and returns a list of matching users.
     *
     * @param filterSearch The filter criteria as a FilterSearch object.
     * @return ResponseEntity containing a list of Users matching the filter criteria.
     */
    @PostMapping("searchUserByFilter")
    public ResponseEntity<List<Users>> searchUserByFilter(@RequestBody FilterSearch filterSearch) {
        return userService.searchUserByFilter(filterSearch);
    }
    
    /**
     * Endpoint for retrieving a user by ID.
     * Accepts a user ID and returns the corresponding user details.
     *
     * @param id The ID of the user to retrieve.
     * @return ResponseEntity containing an Optional<Users> object representing the user details.
     */
    @GetMapping("getUserById/{id}")
    public ResponseEntity<Optional<Users>> getUserById(@PathVariable("id") Integer id) {
        return userService.getUserById(id);
    }
    
    /**
     * Endpoint for updating a user.
     * Accepts a user ID and updated user details as a JSON payload.
     * Updates the user with the provided ID using the new details.
     *
     * @param id The ID of the user to update.
     * @param users The updated user details as a JSON payload.
     * @return ResponseEntity containing a CustomResponse indicating the status of the operation.
     */
    @PutMapping("updateUser/{id}")
    public ResponseEntity<CustomResponse> updateUser(@PathVariable("id") Integer id,@RequestBody Users users) {
        return userService.updateUser(id, users);
    }
    
    /**
     * Endpoint for deleting a user.
     * Accepts a user ID and deletes the user with the provided ID.
     *
     * @param id The ID of the user to delete.
     * @return ResponseEntity containing a CustomResponse indicating the status of the operation.
     */
    @DeleteMapping("deleteUser/{id}")
    public ResponseEntity<CustomResponse> deleteUser(@PathVariable("id") Integer id) {
        return userService.deleteUser(id);
    }
    
}

package com.authenticationServer.objects;

/**
 * LoginRequest class represents a login request object used for user authentication.
 */
public class LoginRequest {

	private String username;
	private String password;
	
	/**
	 * Default constructor.
	 */
	public LoginRequest() {}
	
	/**
	 * Constructor for creating a LoginRequest with specified username and password.
	 *
	 * @param username the username for authentication
	 * @param password the password for authentication
	 */
	public LoginRequest(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	/**
	 * Retrieves the username for authentication.
	 *
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the username for authentication.
	 *
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Retrieves the password for authentication.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password for authentication.
	 *
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}

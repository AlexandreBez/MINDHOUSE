package com.authenticationServer.objects;

public class ResetPasswordRequest {

	private String email;
	private String token;
	private String password;
	
	public ResetPasswordRequest() {}
	
	public ResetPasswordRequest(String email, String token, String password) {
		this.email = email;
		this.token = token;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}

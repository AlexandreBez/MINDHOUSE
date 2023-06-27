package com.authenticationServer.objects;

public class TokenExchange {

	private String token;
	private String email;
	
	public TokenExchange(){}
	
	public TokenExchange(String token, String email) {
		super();
		this.token = token;
		this.email = email;
	}
	
	public String getToken() {
		return token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
}

package com.authenticationServer.objects;

public class UserTableResponse {

	private Integer user_id;
	private String name;
	private String email;
	private String role;
	
	public UserTableResponse() {
		super();
	}

	public UserTableResponse(Integer user_id, String name, String email, String role) {
		super();
		this.user_id = user_id;
		this.name = name;
		this.email = email;
		this.role = role;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
}

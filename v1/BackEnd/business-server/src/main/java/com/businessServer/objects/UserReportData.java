package com.businessServer.objects;

import java.time.LocalDateTime;

/**
 * The UserInfoData class represents user information data.
 */
public class UserReportData {

	private String name;
	private String email;
	private String role;
	private LocalDateTime created_on;
	private String status;
	private LocalDateTime updated_on;

	public UserReportData() {
	}

	public UserReportData(String name, String email, String role, LocalDateTime created_on, String status,
			LocalDateTime updated_on) {
		super();
		this.name = name;
		this.email = email;
		this.role = role;
		this.created_on = created_on;
		this.status = status;
		this.updated_on = updated_on;
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

	public LocalDateTime getCreated_on() {
		return created_on;
	}

	public void setCreated_on(LocalDateTime created_on) {
		this.created_on = created_on;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getUpdated_on() {
		return updated_on;
	}

	public void setUpdated_on(LocalDateTime updated_on) {
		this.updated_on = updated_on;
	}

}

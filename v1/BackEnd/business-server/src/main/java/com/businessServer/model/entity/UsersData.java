package com.businessServer.model.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="USERS")
public class UsersData {

	@Id
	@Column(name="USER_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer user_id;
	
	@Column(name="NAME", nullable = false, length = 100)
	private String name;
	
	@Column(name="EMAIL", nullable = false, length = 100, unique = true)
	private String email;
	
	@Column(name="ROLE", nullable = false, length = 10)
	private String role;
	
	@Column(name="CREATED_ON", nullable = false)
	private LocalDateTime created_on;
	
	@Column(name="STATUS", nullable = false, length = 15)
	private String status;
	
	@Column(name="UPDATED_ON", nullable = true)
	private LocalDateTime updated_on;
	
	public UsersData() {}

	public UsersData(Integer user_id, String name, String email, String role, LocalDateTime created_on, String status,
			LocalDateTime updated_on) {
		super();
		this.user_id = user_id;
		this.name = name;
		this.email = email;
		this.role = role;
		this.created_on = created_on;
		this.status = status;
		this.updated_on = updated_on;
	}
	
	public UsersData(String email,String status,LocalDateTime updated_on) {
		super();
		this.email = email;
		this.status = status;
		this.updated_on = updated_on;
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

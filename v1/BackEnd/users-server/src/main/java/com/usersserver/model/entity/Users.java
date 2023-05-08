package com.usersserver.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="USER")
public class Users {
	
	@Id
	@Column(name="USER_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer user_id;
	
	@Column(name="NAME", nullable = false, length = 100)
	private String name;
	
	@Column(name="EMAIL", nullable = false, length = 150, unique = true)
	private String email;
	
	@Column(name="PASSWORD", nullable = false, length = 250)
	private String password;
	
	@Column(name="ROLE", nullable = false, length = 100)
	private String role;
	
	public Users() {}

	public Users(String name, String email, String password, String role) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
	}
	
	public Users(String email, String password) {
		super();
		this.email = email;
		this.password = password;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
}

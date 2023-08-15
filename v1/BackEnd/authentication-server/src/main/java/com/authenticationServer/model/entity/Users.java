package com.authenticationServer.model.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="USERS")
public class Users implements Serializable{

	@Id
	@Column(name="USER_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer user_id;
	
	@Column(name="NAME", nullable = false, length = 100)
	private String name;
	
	@Column(name="EMAIL", nullable = false, length = 100, unique = true)
	private String email;
	
	@Column(name="PASSWORD", nullable = false, length = 255)
	private String password;
	
	@Column(name="ROLE", nullable = false, length = 10)
	private String role;
	
	@Column(name="IS_TEMP_PASSWORD", nullable = false, columnDefinition = "tinyint")
	private Boolean is_temp_password;
	
	@Column(name="TOKEN_RESET_PASSWORD", nullable = true, columnDefinition = "char", length = 6)
	private String token_reset_password;
	
	@Column(name="EXPIRATION_TIME_RESET_PASSWORD", nullable = true)
	private LocalDateTime expiration_time_reset_password;
	
	@Column(name="CREATED_ON", nullable = false)
	private LocalDateTime created_on;
	
	/**
	 * Default constructor.
	 */
	public Users() {}

	/**
	 * Instantiates a new users.
	 *
	 * @param user_id the user id
	 * @param name the name
	 * @param email the email
	 * @param password the password
	 * @param role the role
	 * @param is_temp_password the is temp password
	 * @param token_reset_password the token reset password
	 * @param expiration_time_reset_password the expiration time reset password
	 * @param created_on the created on
	 */
	public Users(Integer user_id, String name, String email, String password, String role, Boolean is_temp_password,
			String token_reset_password, LocalDateTime expiration_time_reset_password, LocalDateTime created_on) {
		super();
		this.user_id = user_id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
		this.is_temp_password = is_temp_password;
		this.token_reset_password = token_reset_password;
		this.expiration_time_reset_password = expiration_time_reset_password;
		this.created_on = created_on;
	}
	
	/**
	 * Gets the user id.
	 *
	 * @return the user id
	 */
	public Integer getUser_id() {
		return user_id;
	}

	/**
	 * Sets the user id.
	 *
	 * @param user_id the new user id
	 */
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the role.
	 *
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * Sets the role.
	 *
	 * @param role the new role
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * Gets the checks if is temp password.
	 *
	 * @return the checks if is temp password
	 */
	public Boolean getIs_temp_password() {
		return is_temp_password;
	}

	/**
	 * Sets the checks if is temp password.
	 *
	 * @param is_temp_password the new checks if is temp password
	 */
	public void setIs_temp_password(Boolean is_temp_password) {
		this.is_temp_password = is_temp_password;
	}

	/**
	 * Gets the token reset password.
	 *
	 * @return the token reset password
	 */
	public String getToken_reset_password() {
		return token_reset_password;
	}

	/**
	 * Sets the token reset password.
	 *
	 * @param token_reset_password the new token reset password
	 */
	public void setToken_reset_password(String token_reset_password) {
		this.token_reset_password = token_reset_password;
	}

	/**
	 * Gets the expiration time reset password.
	 *
	 * @return the expiration time reset password
	 */
	public LocalDateTime getExpiration_time_reset_password() {
		return expiration_time_reset_password;
	}

	/**
	 * Sets the expiration time reset password.
	 *
	 * @param expiration_time_reset_password the new expiration time reset password
	 */
	public void setExpiration_time_reset_password(LocalDateTime expiration_time_reset_password) {
		this.expiration_time_reset_password = expiration_time_reset_password;
	}


	/**
	 * Gets the created on.
	 *
	 * @return the created on
	 */
	public LocalDateTime getCreated_on() {
		return created_on;
	}

	/**
	 * Sets the created on.
	 *
	 * @param created_on the new created on
	 */
	public void setCreated_on(LocalDateTime created_on) {
		this.created_on = created_on;
	}
	
}

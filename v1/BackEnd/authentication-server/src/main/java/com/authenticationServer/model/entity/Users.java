package com.authenticationServer.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity class representing a User.
 * Annotated with JPA annotations for mapping to database table.
 */
@Entity
@Table(name="USERS")
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
	
	@Column(name="CREATION_DATE", nullable = false, columnDefinition = "char", length = 19)
	private String creation_date;
	
	@Column(name="NUMBER_TOKEN_RESET_PWD", nullable = true, columnDefinition = "char", length = 6)
	private String number_token_reset_pwd;
	
	@Column(name="EXPIRATION_DATE", nullable = true, length = 25)
	private String expiration_date;
	
	/**
	 * Default constructor.
	 */
	public Users() {}

	/**
	 * Constructor for creating a User with specified properties.
	 *
	 * @param name the name of the User
	 * @param email the email of the User
	 * @param password the password of the User
	 * @param role the role of the User
	 * @param creation_date the creation date of the User
	 * @param number_token_reset_pwd the token for reset password
	 * @param expiration_date the expiration date of the token
	 */
	public Users(String name, String email, String password, String role, String creation_date, String number_token_reset_pwd, String expiration_date) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
		this.creation_date = creation_date;
		this.number_token_reset_pwd = number_token_reset_pwd;
		this.expiration_date = expiration_date;
	}
	
	/**
	 * Constructor for creating a User with specified properties.
	 *
	 * @param name the name of the User
	 * @param email the email of the User
	 * @param password the password of the User
	 * @param role the role of the User
	 * @param creation_date the creation date of the User
	 * @param number_token_reset_pwd the token for reset password
	 * @param expiration_date the expiration date of the token
	 */
	public Users(String name, String email, String password, String role, String creation_date) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
		this.creation_date = creation_date;
	}
	
	
	/**
	 * Constructor for creating a User with email and password.
	 *
	 * @param email the email of the User
	 * @param password the password of the User
	 */
	public Users(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	
	/**
	 * Constructor for creating a User with specified properties.
	 *
	 * @param user_id the user ID of the User
	 * @param name the name of the User
	 * @param email the email of the User
	 * @param role the role of the User
	 */
	public Users(Integer user_id, String name, String email, String role) {
		super();
		this.user_id = user_id;
		this.name = name;
		this.email = email;
		this.role = role;
	}
	
	/**
	 * Constructor for creating a User with specified user ID and password.
	 *
	 * @param user_id the user ID of the User
	 * @param password the password of the User
	 */
	public Users(Integer user_id, String password) {
		super();
		this.user_id = user_id;
		this.password = password;
	}

	/**
	 * Retrieves the user ID of the User.
	 *
	 * @return the user ID
	 */
	public Integer getUser_id() {
		return user_id;
	}

	/**
	 * Sets the user ID of the User.
	 *
	 * @param user_id the user ID to set
	 */
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	/**
	 * Retrieves the name of the User.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the User.
	 *
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Retrieves the email of the User.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email of the User.
	 *
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Retrieves the password of the User.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password of the User.
	 *
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Retrieves the role of the User.
	 *
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * Sets the role of the User.
	 *
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * Retrieves the creation date of the User.
	 *
	 * @return the creation date
	 */
	public String getCreation_date() {
		return creation_date;
	}

	/**
	 * Sets the creation date of the User.
	 *
	 * @param creation_date the creation date to set
	 */
	public void setCreation_date(String creation_date) {
		this.creation_date = creation_date;
	}
	
	/**
	 * Retrieves the number_token_reset_pwd of the token.
	 *
	 * @return the number_token_reset_pwd
	 */
	public String getNumber_token_reset_pwd() {
		return number_token_reset_pwd;
	}

	/**
	 * Sets the number_token_reset_pwd of the password.
	 *
	 * @param number_token_reset_pwd the token to set
	 */
	public void setNumber_token_reset_pwd(String number_token_reset_pwd) {
		this.number_token_reset_pwd = number_token_reset_pwd;
	}

	/**
	 * Retrieves the expiration_date of the token expiration date.
	 *
	 * @return the expiration_date
	 */
	public String getExpiration_date() {
		return expiration_date;
	}

	/**
	 * Sets the expiration_date of the token.
	 *
	 * @param expiration_date the expiration date token to set
	 */
	public void setExpiration_date(String expiration_date) {
		this.expiration_date = expiration_date;
	}
	
	
}

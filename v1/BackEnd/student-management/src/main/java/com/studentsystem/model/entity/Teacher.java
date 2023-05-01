package com.studentsystem.model.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TEACHER")
public class Teacher {

	@Id
	@Column(name = "TEACHER_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer teacher_id;
	
	@Column(nullable = false, name = "TEACHER_NAME", length = 100)
	private String teacher_name;
	
	@Column(nullable = false, name = "TEACHER_DOCUMENT", length = 15, unique = true)
	private String teacher_document;
	
	@Column(nullable = false, name = "TEACHER_AGE", length = 2)
	private String teacher_age;
	
	@Column(nullable = false, name = "TEACHER_BIRTHDATE", length = 10)
	private String teacher_birthdate;
	
	@Column(nullable = false, name = "TEACHER_COUNTRY", length = 30)
	private String teacher_country;
	
	@Column(nullable = false, name = "TEACHER_STATE", length = 20)
	private String teacher_state;
	
	@Column(nullable = false, name = "TEACHER_CITY", length = 50)
	private String teacher_city;
	
	@Column(nullable = false, name = "TEACHER_ADRESS", length = 100)
	private String teacher_adress;
	
	@Column(nullable = false, name = "TEACHER_ZIPCODE", length = 20)
	private String teacher_zipcode;
	
	@Column(nullable = false, name = "TEACHER_EMAIL", length = 50, unique = true)
	private String teacher_email;
	
	@Column(nullable = true, name = "TEACHER_PHONE", length = 25)
	private String teacher_phone;
	
	@Column(nullable = true, name = "TEACHER_PHONE_2", length = 25)
	private String teacher_phone_2;
	
	@Column(nullable = false, name = "TEACHER_CREATION_DATE", length = 10)
	private String teacher_creation_date;
	
	@OneToMany(mappedBy = "fk_teacher")
	private List<Classroom> classroom;

	public Teacher() {}

	public Teacher(Integer teacher_id, String teacher_name, String teacher_document, String teacher_age,
			String teacher_birthdate, String teacher_country, String teacher_state, String teacher_city,
			String teacher_adress, String teacher_zipcode, String teacher_email, String teacher_phone,
			String teacher_phone_2, String teacher_creation_date) {
		super();
		this.teacher_id = teacher_id;
		this.teacher_name = teacher_name;
		this.teacher_document = teacher_document;
		this.teacher_age = teacher_age;
		this.teacher_birthdate = teacher_birthdate;
		this.teacher_country = teacher_country;
		this.teacher_state = teacher_state;
		this.teacher_city = teacher_city;
		this.teacher_adress = teacher_adress;
		this.teacher_zipcode = teacher_zipcode;
		this.teacher_email = teacher_email;
		this.teacher_phone = teacher_phone;
		this.teacher_phone_2 = teacher_phone_2;
		this.teacher_creation_date = teacher_creation_date;
	}

	public Integer getTeacher_id() {
		return teacher_id;
	}

	public void setTeacher_id(Integer teacher_id) {
		this.teacher_id = teacher_id;
	}

	public String getTeacher_name() {
		return teacher_name;
	}

	public void setTeacher_name(String teacher_name) {
		this.teacher_name = teacher_name;
	}

	public String getTeacher_document() {
		return teacher_document;
	}

	public void setTeacher_document(String teacher_document) {
		this.teacher_document = teacher_document;
	}

	public String getTeacher_age() {
		return teacher_age;
	}

	public void setTeacher_age(String teacher_age) {
		this.teacher_age = teacher_age;
	}

	public String getTeacher_birthdate() {
		return teacher_birthdate;
	}

	public void setTeacher_birthdate(String teacher_birthdate) {
		this.teacher_birthdate = teacher_birthdate;
	}

	public String getTeacher_country() {
		return teacher_country;
	}

	public void setTeacher_country(String teacher_country) {
		this.teacher_country = teacher_country;
	}

	public String getTeacher_state() {
		return teacher_state;
	}

	public void setTeacher_state(String teacher_state) {
		this.teacher_state = teacher_state;
	}

	public String getTeacher_city() {
		return teacher_city;
	}

	public void setTeacher_city(String teacher_city) {
		this.teacher_city = teacher_city;
	}

	public String getTeacher_adress() {
		return teacher_adress;
	}

	public void setTeacher_adress(String teacher_adress) {
		this.teacher_adress = teacher_adress;
	}

	public String getTeacher_zipcode() {
		return teacher_zipcode;
	}

	public void setTeacher_zipcode(String teacher_zipcode) {
		this.teacher_zipcode = teacher_zipcode;
	}

	public String getTeacher_email() {
		return teacher_email;
	}

	public void setTeacher_email(String teacher_email) {
		this.teacher_email = teacher_email;
	}

	public String getTeacher_phone() {
		return teacher_phone;
	}

	public void setTeacher_phone(String teacher_phone) {
		this.teacher_phone = teacher_phone;
	}

	public String getTeacher_phone_2() {
		return teacher_phone_2;
	}

	public void setTeacher_phone_2(String teacher_phone_2) {
		this.teacher_phone_2 = teacher_phone_2;
	}

	public String getTeacher_creation_date() {
		return teacher_creation_date;
	}

	public void setTeacher_creation_date(String teacher_creation_date) {
		this.teacher_creation_date = teacher_creation_date;
	}
	
}

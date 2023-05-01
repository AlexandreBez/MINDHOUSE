package com.studentsystem.model.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * This class represents a Student entity in the database.
 */
@Entity
@Table(name = "STUDENT")
public class Student{

    /**
     * Unique identifier for the student.
     */
    @Id
    @Column(name = "STUDENT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer student_id;

    /**
     * Name of the student.
     */
    @Column(nullable = false, name = "STUDENT_NAME", length = 100)
    private String student_name;

    /**
     * Unique document number of the student.
     */
    @Column(nullable = false, name = "STUDENT_DOCUMENT", length = 15, unique = true)
    private String student_document;

    /**
     * Age of the student.
     */
    @Column(nullable = false, name = "STUDENT_AGE", length = 2)
    private String student_age;

    /**
     * Date of birth of the student.
     */
    @Column(nullable = false, name = "STUDENT_BIRTHDATE", length = 10)
    private String student_birthdate;

    /**
     * Country of the student.
     */
    @Column(nullable = false, name = "STUDENT_COUNTRY", length = 30)
    private String student_country;

    /**
     * State of the student.
     */
    @Column(nullable = false, name = "STUDENT_STATE", length = 20)
    private String student_state;

    /**
     * City of the student.
     */
    @Column(nullable = false, name = "STUDENT_CITY", length = 50)
    private String student_city;

    /**
     * Address of the student.
     */
    @Column(nullable = false, name = "STUDENT_ADRESS", length = 100)
    private String student_adress;

    /**
     * Zip code of the student.
     */
    @Column(nullable = false, name = "STUDENT_ZIPCODE", length = 20)
    private String student_zipcode;

    /**
     * Email address of the student.
     */
    @Column(nullable = false, name = "STUDENT_EMAIL", length = 50, unique = true)
    private String student_email;

    /**
     * Phone number of the student.
     */
    @Column(nullable = true, name = "STUDENT_PHONE", length = 25)
    private String student_phone;

    /**
     * Secondary phone number of the student.
     */
    @Column(nullable = true, name = "STUDENT_PHONE_2", length = 25)
    private String student_phone_2;

    /**
     * Boolean value indicating whether the student agreed to the contract terms.
     */
    @Column(nullable = false, name = "STUDENT_CONTRACT_TERMS")
    private Boolean student_contract_term;

    /**
     * Date when the student was added to the system.
     */
    @Column(nullable = false, name = "STUDENT_CREATION_DATE", length = 10)
    private String student_creation_date;

    /**
     * List of Payment entities associated with this student.
     */
    @OneToMany(mappedBy = "fk_student")
    private List<Payment> payment;

    /**
     * List of Grade entities associated with this student.
     */
    @OneToMany(mappedBy = "fk_student")
    private List<Grade> grade;
	
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        // Join table name
        name = "STUDENT_CLASSROOM_COURSE",
        // Specify the column that references this entity (Student)
        joinColumns = { @JoinColumn(name = "FK_STUDENT") },
        // Specify the column that references the related entity (Classroom)
        inverseJoinColumns = { @JoinColumn(name = "FK_CLASSROOM") }
    )
    // Set of classrooms associated with this student
    private Set<Classroom> classroom = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        // Join table name
        name = "STUDENT_CLASSROOM_COURSE",
        // Specify the column that references this entity (Student)
        joinColumns = { @JoinColumn(name = "FK_STUDENT") },
        // Specify the column that references the related entity (Course)
        inverseJoinColumns = { @JoinColumn(name = "FK_COURSE") }
    )
    // Set of courses associated with this student
    private Set<Course> course = new HashSet<>();
	
    /**
     * Default constructor for Student class.
     */
	public Student() {}

	/**
	 * Constructs a new Student object with the provided personal information.
	 *
	 * @param student_name the name of the student
	 * @param student_document the document number of the student
	 * @param student_age the age of the student
	 * @param student_birthdate the birthdate of the student
	 * @param student_country the country of the student
	 * @param student_state the state of the student
	 * @param student_city the city of the student
	 * @param student_adress the address of the student
	 * @param student_zipcode the zip code of the student
	 * @param student_email the email of the student
	 * @param student_phone the primary phone number of the student
	 * @param student_phone_2 the secondary phone number of the student
	 * @param student_contract_term the contract term status of the student
	 * @param student_creation_date the creation date of the student's account
	 */
	public Student(String student_name, String student_document, String student_age, String student_birthdate,
			String student_country, String student_state, String student_city, String student_adress,
			String student_zipcode, String student_email, String student_phone, String student_phone_2,
			Boolean student_contract_term, String student_creation_date) {
		super();
		this.student_name = student_name;
		this.student_document = student_document;
		this.student_age = student_age;
		this.student_birthdate = student_birthdate;
		this.student_country = student_country;
		this.student_state = student_state;
		this.student_city = student_city;
		this.student_adress = student_adress;
		this.student_zipcode = student_zipcode;
		this.student_email = student_email;
		this.student_phone = student_phone;
		this.student_phone_2 = student_phone_2;
		this.student_contract_term = student_contract_term;
		this.student_creation_date = student_creation_date;
	}

	public Student(String student_name, String student_document, String student_email) {
		super();
		this.student_name = student_name;
		this.student_document = student_document;
		this.student_email = student_email;
	}

	/**
	 * Returns the ID of the student.
	 * 
	 * @return The ID of the student.
	 */
	public Integer getStudent_id() {
	    return student_id;
	}

	/**
	 * Sets the ID of the student.
	 * 
	 * @param student_id The ID of the student.
	 */
	public void setStudent_id(Integer student_id) {
	    this.student_id = student_id;
	}

	/**
	 * Returns the name of the student.
	 * 
	 * @return The name of the student.
	 */
	public String getStudent_name() {
	    return student_name;
	}

	/**
	 * Sets the name of the student.
	 * 
	 * @param student_name The name of the student.
	 */
	public void setStudent_name(String student_name) {
	    this.student_name = student_name;
	}

	/**
	 * Returns the document number of the student.
	 * 
	 * @return The document number of the student.
	 */
	public String getStudent_document() {
	    return student_document;
	}

	/**
	 * Sets the document number of the student.
	 * 
	 * @param student_document The document number of the student.
	 */
	public void setStudent_document(String student_document) {
	    this.student_document = student_document;
	}

	/**
	 * Returns the age of the student.
	 * 
	 * @return The age of the student.
	 */
	public String getStudent_age() {
	    return student_age;
	}

	/**
	 * Sets the age of the student.
	 * 
	 * @param student_age The age of the student.
	 */
	public void setStudent_age(String student_age) {
	    this.student_age = student_age;
	}

	/**
	 * Returns the birthdate of the student.
	 * 
	 * @return The birthdate of the student.
	 */
	public String getStudent_birthdate() {
	    return student_birthdate;
	}

	/**
	 * Sets the birthdate of the student.
	 * 
	 * @param student_birthdate The birthdate of the student.
	 */
	public void setStudent_birthdate(String student_birthdate) {
	    this.student_birthdate = student_birthdate;
	}

	/**
	 * Returns the country of the student.
	 * 
	 * @return The country of the student.
	 */
	public String getStudent_country() {
	    return student_country;
	}

	/**
	 * Sets the country of the student.
	 * 
	 * @param student_country The country of the student.
	 */
	public void setStudent_country(String student_country) {
	    this.student_country = student_country;
	}

	/**
	 * Returns the state of the student.
	 * 
	 * @return The state of the student.
	 */
	public String getStudent_state() {
	    return student_state;
	}

	/**
	 * Sets the state of the student.
	 * 
	 * @param student_state The state of the student.
	 */
	public void setStudent_state(String student_state) {
	    this.student_state = student_state;
	}

	/**
	 * Returns the city of the student.
	 * 
	 * @return The city of the student.
	 */
	public String getStudent_city() {
	    return student_city;
	}

	/**
	 * Sets the city of the student.
	 * 
	 * @param student_city The city of the student.
	 */
	public void setStudent_city(String student_city) {
	    this.student_city = student_city;
	}

	/**
	 * Returns the address of the student.
	 * 
	 * @return The address of the student.
	 */
	public String getStudent_adress() {
	    return student_adress;
	}

	/**
	 * Sets the address of the student.
	 * 
	 * @param student_adress The address of the student.
	 */
	public void setStudent_adress(String student_adress) {
	    this.student_adress = student_adress;
	}

	/**
	 * Returns the zipcode of the student.
	 * 
	 * @return The zipcode of the student.
	 */
	public String getStudent_zipcode() {
	    return student_zipcode;
	}

	/**
	 * Sets the student's zipcode.
	 * @param student_zipcode the zipcode to set.
	 */
	public void setStudent_zipcode(String student_zipcode) {
	    this.student_zipcode = student_zipcode;
	}

	/**
	 * Gets the student's email.
	 * @return the student's email.
	 */
	public String getStudent_email() {
	    return student_email;
	}

	/**
	 * Sets the student's email.
	 * @param student_email the email to set.
	 */
	public void setStudent_email(String student_email) {
	    this.student_email = student_email;
	}

	/**
	 * Gets the student's phone number.
	 * @return the student's phone number.
	 */
	public String getStudent_phone() {
	    return student_phone;
	}

	/**
	 * Sets the student's phone number.
	 * @param student_phone the phone number to set.
	 */
	public void setStudent_phone(String student_phone) {
	    this.student_phone = student_phone;
	}

	/**
	 * Gets the student's second phone number.
	 * @return the student's second phone number.
	 */
	public String getStudent_phone_2() {
	    return student_phone_2;
	}

	/**
	 * Sets the student's second phone number.
	 * @param student_phone_2 the second phone number to set.
	 */
	public void setStudent_phone_2(String student_phone_2) {
	    this.student_phone_2 = student_phone_2;
	}

	/**
	 * Gets the student's contract term.
	 * @return the student's contract term.
	 */
	public Boolean getStudent_contract_term() {
	    return student_contract_term;
	}

	/**
	 * Sets the student's contract term.
	 * @param student_contract_term the contract term to set.
	 */
	public void setStudent_contract_term(Boolean student_contract_term) {
	    this.student_contract_term = student_contract_term;
	}

	/**
	 * Gets the student's creation date.
	 * @return the student's creation date.
	 */
	public String getStudent_creation_date() {
	    return student_creation_date;
	}

	/**
	 * Sets the student's creation date.
	 * @param student_creation_date the creation date to set.
	 */
	public void setStudent_creation_date(String student_creation_date) {
	    this.student_creation_date = student_creation_date;
	}

}

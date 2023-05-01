package com.studentsystem.model.entity;

import java.math.BigDecimal;
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

@Entity
@Table(name="COURSE")
public class Course {
	
	@Id
	@Column(name = "COURSE_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer course_id;
	
	@Column(nullable = false, name = "COURSE_NAME", length = 60)
	private String course_name;
	
	@Column(nullable = false, name = "COURSE_FIELD", length = 30)
	private String course_field;
	
	@Column(nullable = false, name = "COURSE_PRICE")
	private BigDecimal course_price;
	
	@Column(nullable = false, name = "COURSE_MODALITY", length = 15)
	private String course_modality;
	
	@Column(nullable = false, name = "COURSE_CREATION_DATE", length = 10)
	private String course_creation_date;
	
	@OneToMany(mappedBy = "fk_course", orphanRemoval = false)
	private List<Payment> payment;
	
	@OneToMany(mappedBy = "fk_course", orphanRemoval = false)
	private List<Classroom> classroom;
	
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "STUDENT_CLASSROOM_COURSE",
               joinColumns = { @JoinColumn(name = "FK_COURSE") },
               inverseJoinColumns = { @JoinColumn(name = "FK_STUDENT") })
    private Set<Student> students = new HashSet<>();

	public Course(){}

	public Course(String course_name, String course_field, BigDecimal course_price, String course_modality,
			String course_creation_date) {
		super();
		this.course_name = course_name;
		this.course_field = course_field;
		this.course_price = course_price;
		this.course_modality = course_modality;
		this.course_creation_date = course_creation_date;
	}

	public Integer getCourse_id() {
		return course_id;
	}

	public void setCourse_id(Integer course_id) {
		this.course_id = course_id;
	}

	public String getCourse_name() {
		return course_name;
	}

	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}

	public String getCourse_field() {
		return course_field;
	}

	public void setCourse_field(String course_field) {
		this.course_field = course_field;
	}

	public BigDecimal getCourse_price() {
		return course_price;
	}

	public void setCourse_price(BigDecimal course_price) {
		this.course_price = course_price;
	}

	public String getCourse_modality() {
		return course_modality;
	}

	public void setCourse_modality(String course_modality) {
		this.course_modality = course_modality;
	}

	public String getCourse_creation_date() {
		return course_creation_date;
	}

	public void setCourse_creation_date(String course_creation_date) {
		this.course_creation_date = course_creation_date;
	}

	
}

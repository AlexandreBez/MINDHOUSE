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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "CLASSROOM")
public class Classroom {
	
	@Id
	@Column(name = "CLASSROOM_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer classroom_id;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "fk_teacher", nullable = false)
	private Teacher fk_teacher;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "fk_course", nullable = false)
	private Course fk_course;
	
	@Column(nullable = false, name = "CLASSROOM_PERIOD", length = 20)
	private String classroom_period;
	
	@Column(nullable = false, name = "CLASSROOM_START_TIME", length = 10)
	private String classroom_start_time;
	
	@Column(nullable = false, name = "CLASSROOM_END_TIME", length = 10)
	private String classroom_end_time;
	
	@Column(nullable = false, name = "CLASSROOM_START_DATE", length = 10)
	private String classroom_start_date;
	
	@Column(nullable = true, name = "CLASSROOM_END_DATE", length = 10)
	private String classroom_end_date;
	
	@Column(nullable = false, name = "CLASSROOM_LIMIT")
	private Integer classroom_limit;
	
	@Column(nullable = false, name = "CLASSROOM_QTD_STUDENTS")
	private Integer classroom_qtd_student;
	
	@Column(nullable = false, name = "CLASSROOM_CREATION_DATE", length = 10)
	private String classroom_creation_date;
	
	@OneToMany(mappedBy = "fk_classroom")
	private List<Grade> grade;
	
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "STUDENT_CLASSROOM_COURSE",
               joinColumns = { @JoinColumn(name = "FK_CLASSROOM") },
               inverseJoinColumns = { @JoinColumn(name = "FK_STUDENT") })
    private Set<Student> students = new HashSet<>();

	public Classroom() {}
	
	public Classroom(Teacher fk_teacher, Course fk_course, String classroom_period,
			String classroom_start_time, String classroom_end_time, String classroom_start_date,
			String classroom_end_date, Integer classroom_limit, Integer classroom_qtd_student,
			String classroom_creation_date) {
		super();
		this.fk_teacher = fk_teacher;
		this.fk_course = fk_course;
		this.classroom_period = classroom_period;
		this.classroom_start_time = classroom_start_time;
		this.classroom_end_time = classroom_end_time;
		this.classroom_start_date = classroom_start_date;
		this.classroom_end_date = classroom_end_date;
		this.classroom_limit = classroom_limit;
		this.classroom_qtd_student = classroom_qtd_student;
		this.classroom_creation_date = classroom_creation_date;
	}

	public Integer getClassroom_id() {
		return classroom_id;
	}

	public void setClassroom_id(Integer classroom_id) {
		this.classroom_id = classroom_id;
	}

	public Teacher getFk_teacher() {
		return fk_teacher;
	}

	public void setFk_teacher(Teacher fk_teacher) {
		this.fk_teacher = fk_teacher;
	}

	public Course getFk_course() {
		return fk_course;
	}

	public void setFk_course(Course fk_course) {
		this.fk_course = fk_course;
	}

	public String getClassroom_period() {
		return classroom_period;
	}

	public void setClassroom_period(String classroom_period) {
		this.classroom_period = classroom_period;
	}

	public String getClassroom_start_time() {
		return classroom_start_time;
	}

	public void setClassroom_start_time(String classroom_start_time) {
		this.classroom_start_time = classroom_start_time;
	}

	public String getClassroom_end_time() {
		return classroom_end_time;
	}

	public void setClassroom_end_time(String classroom_end_time) {
		this.classroom_end_time = classroom_end_time;
	}

	public String getClassroom_start_date() {
		return classroom_start_date;
	}

	public void setClassroom_start_date(String classroom_start_date) {
		this.classroom_start_date = classroom_start_date;
	}

	public String getClassroom_end_date() {
		return classroom_end_date;
	}

	public void setClassroom_end_date(String classroom_end_date) {
		this.classroom_end_date = classroom_end_date;
	}

	public Integer getClassroom_limit() {
		return classroom_limit;
	}

	public void setClassroom_limit(Integer classroom_limit) {
		this.classroom_limit = classroom_limit;
	}

	public Integer getClassroom_qtd_student() {
		return classroom_qtd_student;
	}

	public void setClassroom_qtd_student(Integer classroom_qtd_student) {
		this.classroom_qtd_student = classroom_qtd_student;
	}

	public String getClassroom_creation_date() {
		return classroom_creation_date;
	}

	public void setClassroom_creation_date(String classroom_creation_date) {
		this.classroom_creation_date = classroom_creation_date;
	}
	
}

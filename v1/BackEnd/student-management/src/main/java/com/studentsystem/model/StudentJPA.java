package com.studentsystem.model;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.studentsystem.model.entity.Student;

@Repository
public interface StudentJPA extends JpaRepository<Student, Integer> {
	
	@Query("SELECT u FROM Student u WHERE u.student_name LIKE %:name% ORDER BY u.student_name")
	List<Student> findStudentByName(@Param("name") String name);
	
	@Query("SELECT u FROM Student u WHERE u.student_document LIKE %:document% ORDER BY u.student_name")
	List<Student> findStudentByDocument(@Param("document") String document);
	
	@Query("SELECT u FROM Student u ORDER BY u.student_name")
	Page<Student> findAll(Pageable pageable);
}
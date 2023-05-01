package com.studentsystem.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.studentsystem.model.entity.Course;

public interface CourseJPA extends JpaRepository<Course, Integer> {

	@Query("Select u from Course u where u.course_name like :name% group by u.course_name")
	List<Course> findCourseByName(@Param("name") String name);

	@Query("Select u from Course u where u.course_field like :field% group by u.course_name")
	List<Course> findCourseByField(@Param("field") String field);

	@Query("Select u from Course u where u.course_modality like :modality% group by u.course_name")
	List<Course> findCourseByModality(@Param("modality") String modality);

}

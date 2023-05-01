package com.studentsystem.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.studentsystem.model.entity.Student_Classroom_Course;

public interface StudentClassroomCourseJPA extends JpaRepository<Student_Classroom_Course, Integer>{

    @Query(
    	"SELECT "
    	+"r.classroom_start_date "
    	+"FROM Student_Classroom_Course e "
    	+"INNER JOIN Classroom r "
    	+"ON e.fk_classroom = r.classroom_id "
    	+"WHERE e.student_classroom_course_id = :id"
	)
	List<Object[]> getStudentCourseStartDateByStudentClassroomCourseId(@Param("id") Integer id);
	
    @Query(
    	"SELECT "
    	+"e.student_name,"
    	+"e.student_document,"
    	+"e.student_email,"
    	+"c.course_name,"
    	+"c.course_price "
    	+"FROM Student e "
    	+"INNER JOIN Student_Classroom_Course r ON r.fk_student = e.student_id "
    	+"INNER JOIN Course c ON r.fk_course = c.course_id "
    	+"WHERE r.student_classroom_course_id = :id"
	)
	List<Object[]> getStudentEmailByStudentClassroomCourseId(@Param("id") Integer id);
	
	@Query(value = 
			"SELECT "
			+ "e.student_classroom_course_id,"
			+ "c.course_id,"
			+ "c.course_name,"
			+ "c.course_field,"
			+ "c.course_modality,"
			+ "r.classroom_start_date,"
			+ "r.classroom_end_date "
	        + "FROM Student_Classroom_Course e "
	        + "INNER JOIN Course c ON e.fk_course = c.course_id "
	        + "INNER JOIN Classroom r ON e.fk_classroom = r.classroom_id "
	        + "LEFT JOIN Payment p ON p.fk_student = e.fk_student "
	        + "AND p.fk_course = e.fk_course "
	        + "LEFT JOIN Grade g ON g.fk_student = e.fk_student "
	        + "AND g.fk_classroom = e.fk_classroom "
	        + "WHERE e.fk_student = :id "
	        + "ORDER BY c.course_name", 
	        nativeQuery = true)
	List<Object[]> getStudentCoursesByStudentId(@Param("id") Integer id);

    @Query(
    		"SELECT " 
    		+ "e.student_classroom_course_id,"
    		+ "c.course_id,"
    		+ "c.course_name,"
    		+ "c.course_field,"
    		+ "c.course_modality,"
    		+ "r.classroom_start_date,"
    		+ "r.classroom_end_date "
    		+"FROM Student_Classroom_Course e "
    		+"INNER JOIN Course c ON e.fk_course = c.course_id "
    		+"INNER JOIN Classroom r ON e.fk_classroom = r.classroom_id "
    		+"WHERE e.fk_student = :id "
    		+"AND c.course_name like %:name% "
    		+"ORDER BY r.classroom_start_date" 
	)
	List<Object[]> getStudentCoursesByCourseNameAndStudentId(@Param("id") Integer id, @Param("name") String name);

	@Query(value =
		"SELECT "
		+"s.student_email,"
		+"s.student_document,"
		+"c.course_name,"
		+"c.course_field,"
		+"c.course_price,"
		+"c.course_modality,"
		+"r.classroom_period,"
		+"r.classroom_start_time,"
		+"r.classroom_end_time,"
		+"r.classroom_start_date,"
		+"r.classroom_end_date,"
		+"t.teacher_name "
		+"FROM Student_Classroom_Course e "
		+"INNER JOIN Course c ON e.fk_course = c.course_id "
		+"INNER JOIN Classroom r ON e.fk_classroom = r.classroom_id "
		+"INNER JOIN Teacher t ON r.fk_teacher = t.teacher_id "
		+"INNER JOIN Student s ON e.fk_student = s.student_id "
		+"WHERE e.student_classroom_course_id = :id",
		nativeQuery = true
	)
	List<Object[]> getAllCourseInfoByStudentClassroomCourseId(@Param("id") Integer id);
	
}

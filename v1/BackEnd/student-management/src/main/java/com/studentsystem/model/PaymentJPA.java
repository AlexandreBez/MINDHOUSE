package com.studentsystem.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.studentsystem.model.entity.Payment;

public interface PaymentJPA extends JpaRepository<Payment, Integer> {

	@Query(value = "SELECT p.payment_id, c.course_name, c.course_price, r.promotion_name, r.promotion_descount_amount, p.payment_total_amount, p.payment_method, p.payment_status, p.payment_creation_date\r\n"
			+ "FROM Payment p\r\n" + "INNER JOIN Student s ON p.fk_student = s.student_id\r\n"
			+ "INNER JOIN Course c ON p.fk_course = c.course_id\r\n"
			+ "LEFT JOIN Promotion r ON p.fk_promotion = r.promotion_id\r\n"
			+ "WHERE p.fk_student = :id ORDER BY p.payment_creation_date DESC", nativeQuery = true)
	List<Object[]> getPaymentsByStudentId(@Param("id") Integer id);

	@Query(value = "SELECT p.payment_id, c.course_name, c.course_price, r.promotion_name, r.promotion_descount_amount, p.payment_total_amount, p.payment_method, p.payment_status, p.payment_creation_date\r\n"
			+ "FROM Payment p\r\n" + "INNER JOIN Student s ON p.fk_student = s.student_id\r\n"
			+ "INNER JOIN Course c ON p.fk_course = c.course_id\r\n"
			+ "LEFT JOIN Promotion r ON p.fk_promotion = r.promotion_id\r\n"
			+ "WHERE p.payment_creation_date BETWEEN :from AND :until AND p.fk_student = :id ORDER BY p.payment_creation_date", nativeQuery = true)
	List<Object[]> getPaymentsByStudentIdAndReceipDate(@Param("id") Integer id, @Param("from") String from,
			@Param("until") String until);

	@Query(value = "SELECT c.course_name, c.course_price, r.promotion_name, r.promotion_descount_amount, p.payment_total_amount, p.payment_method, p.payment_status, p.payment_creation_date, s.student_email\r\n"
	        + "FROM Payment p\r\n" 
	        + "INNER JOIN Student s ON p.fk_student = s.student_id\r\n"
	        + "INNER JOIN Course c ON p.fk_course = c.course_id\r\n"
	        + "LEFT JOIN Promotion r ON p.fk_promotion = r.promotion_id\r\n"
	        + "WHERE p.payment_id = :id", nativeQuery = true)
	List<Object[]> getPaymentsByIdAndSendByEmail(@Param("id") Integer id);
}

package com.businessServer.model;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.businessServer.model.entity.Users;
import com.businessServer.objects.UserReportData;

/**
 * Repository interface for performing data operations on
 * Authentication_and_security_user entities. Extends JpaRepository interface.
 * Provides additional custom query methods.
 */
public interface UserJPA extends JpaRepository<Users, Integer> {

	@Query(value = "SELECT ROLE, COUNT(*) as QTD FROM users WHERE STATUS = 'ACTIVE' GROUP BY ROLE", nativeQuery = true)
	List<Object[]> getUserQtdRolesData();
	
	@Query(value = "SELECT EMAIL FROM users WHERE STATUS = 'ACTIVE' ORDER BY EMAIL", nativeQuery = true)
	Optional<String[]> getListOfEmails();

	List<Users> findByEmail(String email);
}

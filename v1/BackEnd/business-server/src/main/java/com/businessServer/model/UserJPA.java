package com.businessServer.model;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.businessServer.model.entity.UsersData;

/**
 * Repository interface for performing data operations on
 * Authentication_and_security_user entities. Extends JpaRepository interface.
 * Provides additional custom query methods.
 */
public interface UserJPA extends JpaRepository<UsersData, Integer> {

	@Query(value="SELECT ROLE, COUNT(*) as QTD FROM users WHERE STATUS = 'ENABLED' GROUP BY ROLE", nativeQuery=true)
	List<Object[]> getUserQtdRolesData();
	
	@Query(value="SELECT EMAIL FROM users WHERE STATUS = 'ENABLED' ORDER BY EMAIL", nativeQuery=true)
	Optional<String[]> getListOfEmails();

	@Query(value="SELECT * FROM users WHERE EMAIL = :email", nativeQuery=true)
	List<UsersData> findByEmail(@Param("email") String email);
}

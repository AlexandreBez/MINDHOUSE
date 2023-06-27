package com.businessIntelligenceServer.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.businessIntelligenceServer.model.entity.Authentication_and_security_user;

/**
 * Repository interface for performing data operations on Authentication_and_security_user entities.
 * Extends JpaRepository interface.
 * Provides additional custom query methods.
 */
public interface Authentication_and_security_usersJPA extends JpaRepository<Authentication_and_security_user, Integer>{
	
    /**
     * Retrieves user quantity data grouped by role.
     *
     * @return a list of objects containing role and user quantity data
     */
	@Query(value="SELECT ROLE, COUNT(*) as QTD FROM authentication_and_security_users GROUP BY ROLE", nativeQuery = true)
	List<Object[]> getUserQtdRolesData();
	
    /**
     * Retrieves users data grouped by role.
     *
     * @return a list of objects containing users data
     */
	@Query(value="SELECT NAME,EMAIL,ROLE,CREATION_DATE FROM authentication_and_security_users ORDER BY ROLE ASC", nativeQuery = true)
	List<Object[]> getUserInfoData();

}

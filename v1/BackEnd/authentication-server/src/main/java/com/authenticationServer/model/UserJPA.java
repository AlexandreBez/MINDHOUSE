package com.authenticationServer.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.authenticationServer.model.entity.Users;

/**
 * Repository interface for performing CRUD operations on Users entities.
 * Extends JpaRepository interface.
 * Provides additional custom query methods.
 */
public interface UserJPA extends JpaRepository<Users, Integer> {
    
    /**
     * Retrieves a list of Users by their email.
     *
     * @param email the email to search for
     * @return the list of Users with the specified email
     */
    List<Users> findByEmail(String email);
    
    
}

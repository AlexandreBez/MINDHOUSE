package com.authenticationServer.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
    
    /**
     * Retrieves user quantity data grouped by role.
     *
     * @return a list of objects containing role and user quantity data
     */
    @Query(value="SELECT ROLE, COUNT(*) as QTD FROM USERS GROUP BY role", nativeQuery = true)
    List<Object[]> getUserQtdRolesData();
    
    /**
     * Searches for users by their name using a filter.
     *
     * @param name the name filter
     * @return a list of objects containing user details (USER_ID, NAME, EMAIL, ROLE)
     */
    @Query(value="SELECT USER_ID,NAME,EMAIL,ROLE FROM USERS WHERE NAME LIKE :name% ORDER BY NAME ASC", nativeQuery = true)
    List<Object[]> searchUserByFilterName(@Param("name") String name);
    
    /**
     * Searches for users by their email using a filter.
     *
     * @param email the email filter
     * @return a list of objects containing user details (USER_ID, NAME, EMAIL, ROLE)
     */
    @Query(value="SELECT USER_ID,NAME,EMAIL,ROLE FROM USERS WHERE EMAIL LIKE :email% ORDER BY NAME ASC", nativeQuery = true)
    List<Object[]> searchUserByFilterEmail(@Param("email") String email);
    
    /**
     * Searches for users by their role using a filter.
     *
     * @param role the role filter
     * @return a list of objects containing user details (USER_ID, NAME, EMAIL, ROLE)
     */
    @Query(value="SELECT USER_ID,NAME,EMAIL,ROLE FROM USERS WHERE ROLE = :role ORDER BY NAME ASC", nativeQuery = true)
    List<Object[]> searchUserByFilterRole(@Param("role") String role);
    
    /**
     * Save the token and expiration date for reset password
     * 
     * @param token the generated token
     * @param expirationDate the expiration of the token
     * @param email the user email
     */
    @Modifying
    @Query(value="UPDATE USERS SET NUMBER_TOKEN_RESET_PWD = :token, EXPIRATION_DATE = :expirationDate WHERE email = :email", nativeQuery = true)
    void updateTokenAndExpirationDateByUserEmail(@Param("token") String token, @Param("expirationDate") String expirationDate, @Param("email") String email);
    
    /**
     * Save the new password
     * 
     * @param password the new password
     * @param email the user email
     */
    @Modifying
    @Query(value="UPDATE USERS SET PASSWORD = :password, NUMBER_TOKEN_RESET_PWD = null,EXPIRATION_DATE = null WHERE email = :email ", nativeQuery = true)
    void updatedPasswordByResetPage(@Param("password") String password,@Param("email") String email);
    
}

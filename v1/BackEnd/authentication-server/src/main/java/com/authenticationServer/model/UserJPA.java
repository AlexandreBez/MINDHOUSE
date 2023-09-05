package com.authenticationServer.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.authenticationServer.model.entity.Users;

public interface UserJPA extends JpaRepository<Users, Integer> {
    
    List<Users> findByEmail(String email);
    
    @Query("SELECT u.user_id,u.name,u.email,u.role FROM Users u ORDER BY u.name")
    List<Object[]> getUsersTableData();
    
    @Query("SELECT u.user_id,u.name,u.email,u.role FROM Users u WHERE u.name LIKE %:data% ORDER BY u.name")
    List<Object[]> nameFilterSearch(@Param("data") String data);
    
    @Query("SELECT u.user_id,u.name,u.email,u.role FROM Users u WHERE u.email LIKE %:data% ORDER BY u.email")
    List<Object[]> emailFilterSearch(@Param("data") String data);
    
    @Query("SELECT u.user_id,u.name,u.email,u.role FROM Users u WHERE u.role = :data ORDER BY u.role")
    List<Object[]> roleFilterSearch(@Param("data") String data);
   
}

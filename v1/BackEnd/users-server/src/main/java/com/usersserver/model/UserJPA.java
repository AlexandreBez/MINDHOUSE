package com.usersserver.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.usersserver.model.entity.Users;

public interface UserJPA extends JpaRepository<Users, Integer> {
    
	List<Users> findByEmail(String email);

}


package com.userserver.model;

import org.springframework.data.jpa.repository.JpaRepository;

import com.userserver.model.entity.User;

public interface UserJPA extends JpaRepository<User ,Integer>{

	User findByEmail(String email);
}

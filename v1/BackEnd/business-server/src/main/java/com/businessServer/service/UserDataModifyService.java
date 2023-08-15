package com.businessServer.service;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.businessServer.model.UserJPA;
import com.businessServer.model.entity.Users;


@Service
public class UserDataModifyService {

	@Autowired
	UserJPA userRepository;

	@RabbitListener(queues = "USER_MODIFICATION")
	@RabbitHandler
	public void modifyUserInformations(Users user) {
	    try {
	        userRepository.save(user);
	        System.out.println("User saved with success");
	    } catch (Exception e) {
	        System.out.println(e.getMessage());
	    }
	}

	
	@RabbitListener(queues = "USER_PURGE")
	public void purgeUserInformations(Users user) {
		try {
			userRepository.delete(user);
			System.out.println("User deleted with success");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}

package com.businessIntelligenceServer.service;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.businessIntelligenceServer.model.Authentication_and_security_usersJPA;
import com.businessIntelligenceServer.model.entity.Authentication_and_security_user;

/**
 * The AuthenticationAndSecurityBIService class provides functionality for handling authentication and security data in a business intelligence system.
 */
@Service
public class AuthenticationAndSecurityBIService {

    @Autowired
    Authentication_and_security_usersJPA authentication_and_security_users;

    /**
     * Saves user data to the data lake.
     *
     * @param user the user object to be saved
     */
    @RabbitListener(queues = "ADD_USER_BI")
    @RabbitHandler
    public void saveUserBIDataLake(Authentication_and_security_user user) {
        try {
            authentication_and_security_users.save(user);
            System.out.println("User saved with success");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Updates user data in the data lake.
     *
     * @param user the user object to be updated
     */
    @RabbitListener(queues = "UPDATE_USER_BI")
    @RabbitHandler
    public void updateUserBIDataLake(Authentication_and_security_user user) {
        try {
            authentication_and_security_users.save(user);
            System.out.println("User updated with success");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Deletes user data from the data lake.
     *
     * @param id the ID of the user to be deleted
     */
    @RabbitListener(queues = "DELETE_USER_BI")
    @RabbitHandler
    public void deleteUserBIDataLake(Integer id) {
        try {
            authentication_and_security_users.deleteById(id);
            System.out.println("User deleted with success");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

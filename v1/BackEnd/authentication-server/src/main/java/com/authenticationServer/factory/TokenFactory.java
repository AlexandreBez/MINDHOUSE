package com.authenticationServer.factory;

import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class TokenFactory {

	private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public String generateToken() {
        StringBuilder sb = new StringBuilder(6);
        Random random = new Random();
        
        for (int i = 0; i < 6; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            sb.append(randomChar);
        }
        
        return sb.toString();
    }
    
}

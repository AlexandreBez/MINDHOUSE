package com.authenticationServer.factory;

import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class TempPasswordFactory {

	private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%&*";

    public String generateTempPassword() {
        StringBuilder sb = new StringBuilder(12);
        Random random = new Random();
        
        for (int i = 0; i < 12; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            sb.append(randomChar);
        }
        
        return sb.toString();
    }
}

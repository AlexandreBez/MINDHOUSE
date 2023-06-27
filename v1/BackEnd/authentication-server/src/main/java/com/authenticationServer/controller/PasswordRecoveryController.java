package com.authenticationServer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.authenticationServer.objects.CustomResponse;
import com.authenticationServer.objects.ResetPwdHelper;
import com.authenticationServer.service.PasswordRecoveryService;

/**
 * The PasswordRecoveryController class handles the endpoints related to password recovery and reset.
 */
@RestController
@RequestMapping("/reset-password/v1/")
public class PasswordRecoveryController {
	
	@Autowired
	PasswordRecoveryService passwordRecoveryService;

	/**
	 * Generates a reset password token and sends it to the specified email address.
	 *
	 * @param email the email address for which to generate the reset password token
	 * @return the ResponseEntity with the response of the token generation and email sending
	 */
    @GetMapping("SendToken/{email}")
    public ResponseEntity<CustomResponse> generateTokenForResetPasswordAndSendByEmail(@PathVariable("email") String email) {
        return passwordRecoveryService.generateTokenForResetPasswordAndSendByEmail(email);
    }
    

    /**
     * Validates a reset password token for the specified email address.
     *
     * @param token the reset password token to validate
     * @param email the email address associated with the token
     * @return the ResponseEntity with the response of the token validation
     */
    @GetMapping("ValidateToken/{token}/{email}")
    public ResponseEntity<CustomResponse> validateToken(@PathVariable("token") String token, @PathVariable("email") String email) {
        return passwordRecoveryService.validateToken(token, email);
    }
    
    /**
     * Updates the password using the reset password token and the new password.
     *
     * @param resetPwdHelper the object containing the reset password token, new password, and email
     * @return the ResponseEntity with the response of the password update
     */
    @PostMapping("ResetPassword")
    public ResponseEntity<CustomResponse> updatedPasswordByResetPage(@RequestBody ResetPwdHelper resetPwdHelper) {
        return passwordRecoveryService.updatedPasswordByResetPage(resetPwdHelper);
    }
}

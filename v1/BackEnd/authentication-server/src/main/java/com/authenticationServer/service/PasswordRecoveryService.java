package com.authenticationServer.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.authenticationServer.factory.TokenFactory;
import com.authenticationServer.feignClients.EmailServerClient;
import com.authenticationServer.model.UserJPA;
import com.authenticationServer.model.entity.Users;
import com.authenticationServer.objects.CustomResponse;
import com.authenticationServer.objects.ResetPwdHelper;
import com.authenticationServer.objects.ResetedUser;
import com.authenticationServer.objects.TokenExchange;

/**
 * The PasswordRecoveryService class handles password recovery and reset operations.
 */
@Service
public class PasswordRecoveryService {

	@Autowired
	TokenFactory tokenFactory;

	@Autowired
	EmailServerClient emailServerClient;

	@Autowired
	UserJPA userRepository;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	RabbitTemplate rabbitTemplate;

	/**
	 * Generates a reset password token and sends it to the specified email address.
	 *
	 * @param email the email address for which to generate the reset password token
	 * @return the ResponseEntity with the response of the token generation and email sending
	 */
	@Transactional
	public ResponseEntity<CustomResponse> generateTokenForResetPasswordAndSendByEmail(String email) {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedDateTime = now.format(formatter);
		try {

			CustomResponse customResponse = new CustomResponse();

			List<Users> emailData = userRepository.findByEmail(email);

			if (emailData.isEmpty()) {
				customResponse.setMessage("Email not found...");
				customResponse.setStatusCode(404);
				customResponse.setTimestamp(formattedDateTime);
				return new ResponseEntity<>(customResponse, HttpStatus.NOT_FOUND);
			}

			// Generate a random token with numbers and letters
			String token = tokenFactory.generateToken();
			TokenExchange tokenExchange = new TokenExchange(token, email);

			customResponse = emailServerClient.sendTokenForEmail(tokenExchange).getBody();

			if (customResponse.getStatusCode() != 200) {
				return new ResponseEntity<>(customResponse, HttpStatus.INTERNAL_SERVER_ERROR);
			}

			LocalDateTime dateNowForToken = LocalDateTime.now();
			dateNowForToken = dateNowForToken.plusMinutes(30);
			String tokenExpirationDate = dateNowForToken.format(formatter);

			userRepository.updateTokenAndExpirationDateByUserEmail(tokenExchange.getToken(), tokenExpirationDate,
					email);

			customResponse.setMessage("Token sent to email with success...");
			customResponse.setStatusCode(200);
			customResponse.setTimestamp(formattedDateTime);

			return new ResponseEntity<>(customResponse, HttpStatus.OK);
		} catch (Exception e) {
			CustomResponse customResponse = new CustomResponse();
			customResponse.setMessage(e.getMessage());
			customResponse.setStatusCode(500);
			customResponse.setTimestamp(formattedDateTime);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Validates a reset password token for the specified email address.
	 *
	 * @param token the reset password token to validate
	 * @param email the email address associated with the token
	 * @return the ResponseEntity with the response of the token validation
	 */
	@Transactional
	public ResponseEntity<CustomResponse> validateToken(String token, String email) {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedDateTime = now.format(formatter);
		try {

			CustomResponse customResponse = new CustomResponse();

			List<Users> emailData = userRepository.findByEmail(email);

			LocalDateTime expirationDateTime = LocalDateTime.parse(emailData.get(0).getExpiration_date(), formatter);

			if (emailData.isEmpty()) {
				customResponse.setMessage("Email not found...");
				customResponse.setStatusCode(404);
				customResponse.setTimestamp(formattedDateTime);
				return new ResponseEntity<>(customResponse, HttpStatus.NOT_FOUND);
			} else if (!emailData.get(0).getNumber_token_reset_pwd().equals(token)
					|| expirationDateTime.isBefore(now)) {
				customResponse.setMessage("Token is invalid...");
				customResponse.setStatusCode(400);
				customResponse.setTimestamp(formattedDateTime);
				return new ResponseEntity<>(customResponse, HttpStatus.BAD_REQUEST);
			}

			customResponse.setMessage("Token is valid...");
			customResponse.setStatusCode(200);
			customResponse.setTimestamp(formattedDateTime);

			return new ResponseEntity<>(customResponse, HttpStatus.OK);

		} catch (Exception e) {
			CustomResponse customResponse = new CustomResponse();
			customResponse.setMessage(e.getMessage());
			customResponse.setStatusCode(500);
			customResponse.setTimestamp(formattedDateTime);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Updates the password using the reset password token and the new password.
	 *
	 * @param resetPwdHelper the object containing the reset password token, new password, and email
	 * @return the ResponseEntity with the response of the password update
	 */
	@Transactional
	public ResponseEntity<CustomResponse> updatedPasswordByResetPage(ResetPwdHelper resetPwdHelper) {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedDateTime = now.format(formatter);
		try {

			CustomResponse customResponse = new CustomResponse();

			List<Users> emailData = userRepository.findByEmail(resetPwdHelper.getEmail());

			if (emailData.isEmpty()) {
				customResponse.setMessage("Email not found...");
				customResponse.setStatusCode(404);
				customResponse.setTimestamp(formattedDateTime);
				return new ResponseEntity<>(customResponse, HttpStatus.NOT_FOUND);
			} else if (!emailData.get(0).getNumber_token_reset_pwd().equals(resetPwdHelper.getToken())) {
				customResponse.setMessage("Token is invalid...");
				customResponse.setStatusCode(400);
				customResponse.setTimestamp(formattedDateTime);
				return new ResponseEntity<>(customResponse, HttpStatus.BAD_REQUEST);
			}

			String newPassword = bCryptPasswordEncoder.encode(resetPwdHelper.getPassword()).toString();

			userRepository.updatedPasswordByResetPage(newPassword, resetPwdHelper.getEmail());

			emailData.get(0).setPassword(newPassword);

			try {
				rabbitTemplate.convertAndSend("Data_to_business_server", "user.update.bi",
						new ResetedUser(emailData.get(0).getUser_id(), emailData.get(0).getName(),
								emailData.get(0).getEmail(), newPassword, emailData.get(0).getRole(),
								emailData.get(0).getCreation_date()));
			} catch (AmqpException e) {
				customResponse.setMessage(e.getMessage());
				customResponse.setStatusCode(500);
				customResponse.setTimestamp(formattedDateTime);
				return new ResponseEntity<>(customResponse, HttpStatus.INTERNAL_SERVER_ERROR);
			}

			customResponse.setMessage("Updated password with success");
			customResponse.setStatusCode(200);
			customResponse.setTimestamp(formattedDateTime);

			return new ResponseEntity<>(customResponse, HttpStatus.OK);
		} catch (Exception e) {
			CustomResponse customResponse = new CustomResponse();
			customResponse.setMessage(e.getMessage());
			customResponse.setStatusCode(500);
			customResponse.setTimestamp(formattedDateTime);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

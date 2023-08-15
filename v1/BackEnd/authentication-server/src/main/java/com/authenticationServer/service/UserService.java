package com.authenticationServer.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.authenticationServer.factory.TokenFactory;
import com.authenticationServer.feignClients.EmailServer_UserToolsClient;
import com.authenticationServer.model.UserJPA;
import com.authenticationServer.model.entity.Users;
import com.authenticationServer.objects.CustomResponse;
import com.authenticationServer.objects.ResetPasswordRequest;
import com.authenticationServer.objects.TempPasswordRequest;
import com.authenticationServer.security.implementation.UserDetailsServiceImplementation;

/**
 * The Class UserService.
 */
@Service
public class UserService implements UserDetailsServiceImplementation {

	@Autowired
	UserJPA userRepository;

	@Autowired
	RabbitTemplate rabbitTemplate;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	TokenFactory tokenFactory;

	@Autowired
	EmailServer_UserToolsClient emailServer_UserToolsClient;

	public Users findByEmail(String email) {
		List<Users> user = userRepository.findByEmail(email);
		if (user.isEmpty()) {
			throw new EntityNotFoundException();
		} else {
			return user.get(0);
		}
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		List<Users> user = userRepository.findByEmail(username);

		if (user.isEmpty()) {
			throw new UsernameNotFoundException("User not found");
		}
		Users loadedUser = user.get(0);

		// Create a list of GrantedAuthority representing user roles
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(loadedUser.getRole()));

		return new User(loadedUser.getEmail(), loadedUser.getPassword(), true, true, true, true, authorities);
	}

	@Transactional
	public ResponseEntity<CustomResponse> changeTempPassword(TempPasswordRequest tempPasswordRequest) {

		CustomResponse response = new CustomResponse();
		try {

			if (!tempPasswordRequest.getNew_password().equals(tempPasswordRequest.getConfirm_password())
					|| tempPasswordRequest.getId() == null || tempPasswordRequest.getNew_password() == null
					|| tempPasswordRequest.getConfirm_password() == null) {
				response.setMessage("Missing or wrong data sent...Impossible to continue with request");
				response.setStatusCode(400);
				response.setTimestamp(new Date().toString());
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			}

			byte[] decodedIdBytes = Base64.getDecoder().decode(tempPasswordRequest.getId());
			String decodedIdValue = new String(decodedIdBytes);

			Optional<Users> user = userRepository.findById(Integer.parseInt(decodedIdValue));

			if (user.get().getIs_temp_password() == false) {
				response.setMessage("Temporary password already changed...Please use recover/change password instead");
				response.setStatusCode(400);
				response.setTimestamp(new Date().toString());
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			}

			byte[] decodedPasswordBytes = Base64.getDecoder().decode(tempPasswordRequest.getNew_password());
			String decodedPasswordValue = new String(decodedPasswordBytes);

			Users updateUserInfo = new Users();
			updateUserInfo = user.get();
			updateUserInfo.setPassword(bCryptPasswordEncoder.encode(decodedPasswordValue));
			updateUserInfo.setIs_temp_password(false);

			userRepository.save(updateUserInfo);

			response.setMessage("Password changed with success...");
			response.setStatusCode(200);
			response.setTimestamp(new Date().toString());
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			response.setMessage("Oops...something whent wrong");
			response.setStatusCode(500);
			response.setTimestamp(new Date().toString());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Transactional
	public ResponseEntity<CustomResponse> sendToken(String email) {

		CustomResponse response = new CustomResponse();
		try {

			List<Users> user = userRepository.findByEmail(email);

			if (user.isEmpty()) {
				response.setMessage("Email not found...");
				response.setStatusCode(404);
				response.setTimestamp(new Date().toString());
				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			}

			String generatedToken = tokenFactory.generateToken();

			CustomResponse emailResponse = emailServer_UserToolsClient.changeTempPassword(email, generatedToken)
					.getBody();

			if (emailResponse.getStatusCode() != 200) {
				return new ResponseEntity<>(emailResponse, HttpStatus.INTERNAL_SERVER_ERROR);
			}

			Users savetoken = user.get(0);
			savetoken.setToken_reset_password(generatedToken);
			savetoken.setExpiration_time_reset_password(LocalDateTime.now().plusHours(1));

			userRepository.save(savetoken);

			response.setMessage("Token sent with success...");
			response.setStatusCode(200);
			response.setTimestamp(new Date().toString());
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			response.setMessage("Oops...something whent wrong");
			response.setStatusCode(500);
			response.setTimestamp(new Date().toString());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Transactional
	public ResponseEntity<CustomResponse> validateToken(String email, String token) {

		CustomResponse response = new CustomResponse();
		try {

			if (email.isEmpty() || token.isEmpty()) {
				response.setMessage("Data sent is empty...Server can't finish request");
				response.setStatusCode(400);
				response.setTimestamp(new Date().toString());
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			}

			List<Users> user = userRepository.findByEmail(email);

			if (!user.get(0).getExpiration_time_reset_password().isAfter(LocalDateTime.now())) {
				response.setMessage("Token is already expired...");
				response.setStatusCode(401);
				response.setTimestamp(new Date().toString());
				return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
			}

			if (!user.get(0).getToken_reset_password().equals(token)) {
				response.setMessage("Token is invalid...Please, send a valid token");
				response.setStatusCode(401);
				response.setTimestamp(new Date().toString());
				return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
			}

			response.setMessage("Token validated with success...");
			response.setStatusCode(200);
			response.setTimestamp(new Date().toString());
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.setMessage(e.getMessage());
			response.setStatusCode(500);
			response.setTimestamp(new Date().toString());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Transactional
	public ResponseEntity<CustomResponse> resendToken(String email) {

		CustomResponse response = new CustomResponse();
		try {

			if (email.isEmpty()) {
				response.setMessage("Data sent is empty...Server can't finish request");
				response.setStatusCode(400);
				response.setTimestamp(new Date().toString());
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			}

			List<Users> user = userRepository.findByEmail(email);

			if (user.isEmpty()) {
				response.setMessage("The email was not found...");
				response.setStatusCode(404);
				response.setTimestamp(new Date().toString());
				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			}
			
			CustomResponse emailResponse = emailServer_UserToolsClient.changeTempPassword(email, user.get(0).getToken_reset_password()).getBody();

			if (emailResponse.getStatusCode() != 200) {
				return new ResponseEntity<>(emailResponse, HttpStatus.INTERNAL_SERVER_ERROR);
			}

			response.setMessage("Email resent with success...");
			response.setStatusCode(200);
			response.setTimestamp(new Date().toString());
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.setMessage(e.getMessage());
			response.setStatusCode(500);
			response.setTimestamp(new Date().toString());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Transactional
	public ResponseEntity<CustomResponse> resetPassword(ResetPasswordRequest resetPasswordRequest) {

		CustomResponse response = new CustomResponse();
		try {
			
			if (resetPasswordRequest.getEmail().isEmpty() || resetPasswordRequest.getPassword().isEmpty() || resetPasswordRequest.getToken().isEmpty()) {
				response.setMessage("Data sent is empty...Server can't finish request");
				response.setStatusCode(400);
				response.setTimestamp(new Date().toString());
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			}
					
			List<Users> user = userRepository.findByEmail(resetPasswordRequest.getEmail());
			
			if(!resetPasswordRequest.getToken().equals(user.get(0).getToken_reset_password())) {
				response.setMessage("Tokens are not equals...");
				response.setStatusCode(400);
				response.setTimestamp(new Date().toString());
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			}
			
			Users updatedInfo = user.get(0);
			updatedInfo.setExpiration_time_reset_password(null);
			updatedInfo.setToken_reset_password(null);
			updatedInfo.setPassword(bCryptPasswordEncoder.encode(resetPasswordRequest.getPassword()));

			userRepository.save(updatedInfo);
			
			response.setMessage("Password updated with success...");
			response.setStatusCode(200);
			response.setTimestamp(new Date().toString());
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.setMessage(e.getMessage());
			response.setStatusCode(500);
			response.setTimestamp(new Date().toString());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}

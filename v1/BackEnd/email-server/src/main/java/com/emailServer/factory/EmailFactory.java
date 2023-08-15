package com.emailServer.factory;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailFactory {

	@Autowired
	JavaMailSender emailSender;

	@Autowired
	Environment environment;

	public void emailNoReplyGenerator(String to, String subject, String text, String pathToAttachment, String fileName)
			throws MessagingException {

		MimeMessage message = emailSender.createMimeMessage();

		// pass 'true' to the constructor to create a multipart message
		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		helper.setFrom(environment.getProperty("spring.mail.username"));
		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(text, true);

		if (pathToAttachment != null && !pathToAttachment.isEmpty()) {
			FileSystemResource file = new FileSystemResource(new File(pathToAttachment));
			if (fileName != null && !fileName.isEmpty()) {
				helper.addAttachment(fileName, file);
			} else {
				helper.addAttachment(file.getFilename(), file);
			}
		}

		emailSender.send(message);
	}

}

package com.studenthome.emailsender.emailFactory;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailFactory {

	@Autowired
	JavaMailSender emailSender;

	public void emailGenerator(String to, String subject, String text, String pathToAttachment, String fileName) throws MessagingException {
		
		MimeMessage message = emailSender.createMimeMessage();

		// pass 'true' to the constructor to create a multipart message
		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		helper.setFrom("StudentManagementTeam@outlook.com");
		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(text, true);

		if (pathToAttachment != null) {
			FileSystemResource file = new FileSystemResource(new File(pathToAttachment));
			helper.addAttachment(fileName + ".pdf", file);
		}

		emailSender.send(message);
	}
}

package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.example.demo.Repo.SendEmailRepo;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private SendEmailRepo sendEmailRepo;
	
	public void SendEmail(String to, String subject, String text) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(to);
		message.setSubject(subject);
		message.setText(text);
		javaMailSender.send(message);
	}
	
	public void SendEmailByHTML(String to, String subject, String HTMLtext) throws MessagingException {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper help = new MimeMessageHelper(message, "utf-8");
		help.setFrom("trinhlekhoatest@gmail.com");
		help.setTo(to);
		help.setSubject(subject);
		help.setText(HTMLtext,true);
		
		javaMailSender.send(message);
	}
	
	public String findTokenByEmail(String email) {
		return sendEmailRepo.FindTokenByEmail(email);
	}
}

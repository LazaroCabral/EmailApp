package com.lzrc.emailproject.emails.utils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.lzrc.emailproject.db.AbstractEmail;
import com.lzrc.emailproject.db.AbstractEmailErrors;
import com.lzrc.emailproject.db.Account;
import com.lzrc.emailproject.db.Email;
import com.lzrc.emailproject.db.EmailErrors;
import com.lzrc.emailproject.db.ManuallyEmail;
import com.lzrc.emailproject.db.ManuallyEmailErrors;
import com.lzrc.emailproject.db.custom.repositories.CustomAccountRepository;
import com.lzrc.emailproject.db.custom.repositories.CustomEmailRepository;
import com.lzrc.emailproject.db.custom.repositories.CustomManuallyEmailRepository;
import com.lzrc.emailproject.db.embeddables.EmailEmbeddable;
import com.lzrc.emailproject.db.repositories.EmailErrorsRepository;
import com.lzrc.emailproject.db.repositories.EmailRepository;
import com.lzrc.emailproject.db.repositories.ManuallyEmailErrorsRepository;
import com.lzrc.emailproject.email.DTO.EmailError;
import com.lzrc.emailproject.email.DTO.TypeEmailErrorDTO;
import com.lzrc.emailproject.emails.utils.EmailsTemplates;

import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;


@Component
public class SendEmail {
	
	@Getter
	@Setter
	@Value("${spring.mail.username}")
	private String emailOwner;

	@Getter
	@Setter
	@Value("${default_email_model}")
	private String defaultEmailModel;
	
	@Getter
	@Setter
	@Value("${default_email_subject}")
	private String defaultEmailSubject;
		
	@Autowired
	JavaMailSender javaMailSender;
	
	@Autowired
	protected EmailsTemplates emailsTemplates;
	
	/*
	 * used for send the emails, if was send with success
	 * not be nothing
	 * else return a List<TypeEmailErrorDTO<Account>> from emails that
	 * fails
	 * */
	
	public List<TypeEmailErrorDTO<Account>> sendEmails(List<Account> accounts, String emailModelName, String subject) {
		List<TypeEmailErrorDTO<Account>> emailsFails=new ArrayList<>();
		
		String text=null;
		try {
			text = this.emailsTemplates.getEmailTemplate(emailModelName);
		} catch (IOException e) {
			for (Account account : accounts) {
				emailsFails.add(new TypeEmailErrorDTO<Account>(account, EmailError.TEMPLATE_NOT_FOUND));
			}
			e.printStackTrace();
			return emailsFails;
		}
		
		for (Account account : accounts) {
			try {
				sendEmail(account.getEmail(), text, subject);
				
			}catch(MailException e){
				//saveEmailErrors(accounts, subject,this.SEND_FAILURE, emailModelName);
				emailsFails.add(new TypeEmailErrorDTO<Account>(account, EmailError.SEND_FAILURE));
			}
		}
		return emailsFails;
	}
	
	public void sendEmail(String email, String text, String subject) throws MailException {
		
		SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
		simpleMailMessage.setFrom(emailOwner);
		simpleMailMessage.setTo(email);
		simpleMailMessage.setSubject(subject);
		simpleMailMessage.setText(text);
		javaMailSender.send(simpleMailMessage);
		
	}
	
}

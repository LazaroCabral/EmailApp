package com.lzrc.EmailProject;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.util.Optionals;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.lzrc.EmailProject.db.Account;
import com.lzrc.EmailProject.db.Email;
import com.lzrc.EmailProject.db.EmailErrors;
import com.lzrc.EmailProject.db.custom.repositories.CustomAccountRepository;
import com.lzrc.EmailProject.db.custom.repositories.CustomEmailRepository;
import com.lzrc.EmailProject.db.embeddables.EmailEmbeddable;
import com.lzrc.EmailProject.db.repositories.EmailErrorsRepository;
import com.lzrc.EmailProject.db.repositories.EmailRepository;
import com.lzrc.EmailProject.email.DTO.TypeEmailErrorDTO;
import com.lzrc.EmailProject.emails.utils.EmailErrorsManager;
import com.lzrc.EmailProject.emails.utils.EmailsTemplates;
import com.lzrc.EmailProject.emails.utils.SendEmail;

import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;


@Component
@Getter
@Setter
public class SendAutomatedEmails {
	
	@Autowired
	CustomAccountRepository customAccountRepository;
	
	@Autowired
	CustomEmailRepository customEmailRepository;
	
	@Autowired
	EmailErrorsRepository emailErrorsRepository;
	
	/**
	 	*Sending Automated emails
	 **/
	
	@Scheduled(cron = "* * * 1 * *", zone = "America/Sao_Paulo")
	public void automatizedSend() {
//		System.out.println("--------------------RUNNING TASK----------------------");
		sendAutomatedEmails();
				
	}
	
	public void sendAutomatedEmails() {
		SendEmail sendEmail=new SendEmail();
		EmailErrorsManager<Email, EmailErrors> emailsManager=
				new EmailErrorsManager(Email.class, EmailErrors.class, customEmailRepository, 
						customEmailRepository, customEmailRepository);
			
		List<Account> accounts=customAccountRepository.findAll();
	
		List<TypeEmailErrorDTO<Account>> typeEmailErrorsDTOs=
				sendEmail.sendEmails(accounts, sendEmail.getDefaultEmailModel(), sendEmail.getDefaultEmailSubject());
		if(typeEmailErrorsDTOs.isEmpty() == false) {
			emailsManager.saveEmailErrors(typeEmailErrorsDTOs, sendEmail.getDefaultEmailSubject(), sendEmail.getDefaultEmailModel());
			}
		}
	
}

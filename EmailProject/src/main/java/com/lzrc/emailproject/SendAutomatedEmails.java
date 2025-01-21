package com.lzrc.emailproject;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.lzrc.emailproject.db.Account;
import com.lzrc.emailproject.db.Email;
import com.lzrc.emailproject.db.EmailErrors;
import com.lzrc.emailproject.db.custom.repositories.CustomAccountRepository;
import com.lzrc.emailproject.db.custom.repositories.CustomEmailRepository;
import com.lzrc.emailproject.db.repositories.EmailErrorsRepository;
import com.lzrc.emailproject.email.DTO.TypeEmailErrorDTO;
import com.lzrc.emailproject.emails.utils.EmailErrorsManager;
import com.lzrc.emailproject.emails.utils.SendEmail;

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

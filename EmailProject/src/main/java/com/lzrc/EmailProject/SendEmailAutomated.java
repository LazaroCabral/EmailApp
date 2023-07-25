package com.lzrc.EmailProject;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.lzrc.EmailProject.db.Conta;
import com.lzrc.EmailProject.db.Email;
import com.lzrc.EmailProject.db.EmailErrors;
import com.lzrc.EmailProject.db.custom.repositories.CustomContaRepository;
import com.lzrc.EmailProject.db.custom.repositories.CustomEmailErrorsRepository;
import com.lzrc.EmailProject.db.custom.repositories.CustomEmailRepository;
import com.lzrc.EmailProject.db.embeddables.EmailEmbeddable;
import com.lzrc.EmailProject.db.repositories.EmailErrorsRepository;
import com.lzrc.EmailProject.db.repositories.EmailRepository;
import com.lzrc.EmailProject.emails.service.EmailsTemplates;
import com.lzrc.EmailProject.emails.service.SendEmail;

import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;


@Component
@Getter
@Setter
public class SendEmailAutomated extends SendEmail {
	
	@Autowired
	CustomContaRepository customContaRepository;
	
	@Autowired
	CustomEmailRepository customEmailRepository;
	
	@Autowired
	CustomEmailErrorsRepository customEmailErrorsRepository;
	
	@Scheduled(cron = "* * * 1 * *", zone = "America/Sao_Paulo")
	public void automatizedSend() {
//		System.out.println("--------------------RUNNING TASK----------------------");
		List<Conta> contas=customContaRepository.findContasWithEmails(super.getDefaultEmailModel());
		List<Email> customEmailModels=customEmailRepository.findAllDistinctModelEmails();
		
		if(!Optional.ofNullable(customEmailModels).isEmpty() ||
				customEmailModels.size()!=0) {
			
			sendCustomEmails(customEmailModels);
		}
		
		if(Optional.ofNullable(contas).isEmpty() ||
				contas.size()==0) {
//			System.out.println("---without accounts---");
			return;
		}else {
			System.out.println(contas.get(0).getCustomAutomatizedEmails().get(0).getEmailEmbeddable().getEmailModelName());
			try {
				sendEmails(contas, super.getDefaultEmailModel(), super.getDefaultEmailSubject());
			} catch (IOException e) {
				saveEmailErrors(contas, super.getDefaultEmailSubject(), super.MODEL_NOT_FOUND, super.getDefaultEmailModel());
				e.printStackTrace();
			}
		}
		
	}
	@Transactional
	@Override
	public int sendEmails(List<Conta> contas, String emailModelName, String subject) throws IOException {
		int emailsFail=0;
		String text=super.emailsTemplates.getEmailTemplate(emailModelName);
		
		for (Conta conta : contas) {
			try {
				super.sendEmail(conta.getEmail(), text, subject);
				
			}catch(MailException e){
				
				Email email=customEmailRepository.findIdByEmailEmbeddable(new EmailEmbeddable(emailModelName, conta));
				
				if(Optional.ofNullable(email).isEmpty()) {
					customEmailRepository.save(new Email(new EmailEmbeddable(emailModelName,conta),subject));
				}
				
				EmailErrors emailErrors=new EmailErrors(LocalDateTime.now(), 
						super.SEND_FAILURE, email);
				
				customEmailErrorsRepository.save(emailErrors);
				emailsFail++;
			}
		}
		return emailsFail;
	}
	
	@Transactional
	@Override
	public void saveEmailErrors(List<Conta> contas, String subject,String cause, String emailModelName) {
		for (Conta conta : contas) { 
			
			Email email=customEmailRepository.findIdByEmailEmbeddable(new EmailEmbeddable(emailModelName, conta));
			
			if(Optional.ofNullable(email).isEmpty()) {
				customEmailRepository.save(new Email(new EmailEmbeddable(emailModelName,conta),subject));
			}
	
			EmailErrors emailErrors=new EmailErrors(LocalDateTime.now(), 
					cause, email);
			
			customEmailErrorsRepository.save(emailErrors);
			}
			
	}
	
	@Transactional
	private void sendCustomEmails(List<Email> customEmailModels) {
		
		for (Email email : customEmailModels) {
			
			String emailModelName=email.getEmailEmbeddable().getEmailModelName();
			String subject=email.getSubject();
			
			if(emailModelName.equals(super.getDefaultEmailModel())) {
//				System.out.println("---sendCustomEmailsAbort---");
				continue;
			}
		
			List<Conta> contasCustom=customContaRepository.findContasWithEmails(emailModelName);
			
			try {
				sendEmails(contasCustom, emailModelName, subject);
			} catch (IOException e) {
				
				saveCustomizedEmailErrors(contasCustom, super.MODEL_NOT_FOUND);
				
				e.printStackTrace();
			}
		}
	}
	
	@Transactional
	public void saveCustomizedEmailErrors(List<Conta> contas, String cause) {
		for (Conta conta : contas) {
			
			Email email=conta.getCustomAutomatizedEmails().get(0);
			EmailErrors emailErrors=new EmailErrors(LocalDateTime.now(), 
					cause, email);
				
			customEmailErrorsRepository.save(emailErrors);
		}
	}
	
}

package com.lzrc.EmailProject.emails.service;

import java.io.IOException;
import java.time.LocalDateTime;
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

import com.lzrc.EmailProject.db.AbstractEmail;
import com.lzrc.EmailProject.db.AbstractEmailErrors;
import com.lzrc.EmailProject.db.Conta;
import com.lzrc.EmailProject.db.Email;
import com.lzrc.EmailProject.db.EmailErrors;
import com.lzrc.EmailProject.db.ManuallyEmail;
import com.lzrc.EmailProject.db.ManuallyEmailErrors;
import com.lzrc.EmailProject.db.custom.repositories.CustomContaRepository;
import com.lzrc.EmailProject.db.custom.repositories.CustomEmailErrorsRepository;
import com.lzrc.EmailProject.db.custom.repositories.CustomEmailRepository;
import com.lzrc.EmailProject.db.custom.repositories.CustomManuallyEmailErrorsRepository;
import com.lzrc.EmailProject.db.custom.repositories.CustomManuallyEmailRepository;
import com.lzrc.EmailProject.db.embeddables.EmailEmbeddable;
import com.lzrc.EmailProject.db.repositories.EmailErrorsRepository;
import com.lzrc.EmailProject.db.repositories.EmailRepository;
import com.lzrc.EmailProject.emails.service.EmailsTemplates;

import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;


@Component
public class SendEmail {
	
	@Getter
	@Setter
	@Value("${default_email_model}")
	private String defaultEmailModel;
	
	@Getter
	@Setter
	@Value("${default_email_subject}")
	private String defaultEmailSubject;
	
	@Getter
	@Setter
	@Value("${spring.mail.username}")
	private String emailOwner;
	
	public final String SEND_FAILURE="falha_no_envio";
	public final String MODEL_NOT_FOUND="modelo_nao_encontrado";
	public final String NETWORK_FAILURE="falha_no_envio";
		
	@Autowired
	JavaMailSender javaMailSender;
	
	@Autowired
	CustomContaRepository customContaRepository;
	
	@Autowired
	CustomEmailErrorsRepository customEmailErrorsRepository;
	
	@Autowired
	CustomEmailRepository customEmailRepository;
	
	@Autowired
	CustomManuallyEmailRepository customManuallyEmailRepository;
	
	@Autowired
	CustomManuallyEmailErrorsRepository  customManuallyEmailErrorsRepository;
	
	@Autowired
	protected EmailsTemplates emailsTemplates;
	
	public int sendEmails(List<Conta> contas, String emailModelName, String subject) throws IOException{
		int emailsFail=0;
		String text=this.emailsTemplates.getEmailTemplate(emailModelName);
		
		for (Conta conta : contas) {
			try {
				sendEmail(conta.getEmail(), text, subject);
				
			}catch(MailException e){
				saveEmailErrors(contas, subject,this.SEND_FAILURE, emailModelName);
				emailsFail++;
			}
		}
		return emailsFail;
	}
	
	public void saveEmailErrors(List<Conta> contas, String subject, String cause, String emailModelName) {
		for (Conta conta : contas) { 
			
//			Email email=emailRepository.findById(new EmailEmbeddable(emailModelName,conta)).get();
			ManuallyEmail email=customManuallyEmailRepository.findIdByEmailEmbeddable(new EmailEmbeddable(emailModelName, conta));
			
			if(Optional.ofNullable(email).isEmpty()) {
				customManuallyEmailRepository.save(new ManuallyEmail(emailModelName,conta,subject));
				email=customManuallyEmailRepository.findIdByEmailEmbeddable(new EmailEmbeddable(emailModelName, conta));
			}
			
			ManuallyEmailErrors emailErrors=new ManuallyEmailErrors(LocalDateTime.now(), 
					cause, email);
			
			customManuallyEmailErrorsRepository.save(emailErrors);
			}
			
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

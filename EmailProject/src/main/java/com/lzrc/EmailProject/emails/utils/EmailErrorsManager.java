package com.lzrc.EmailProject.emails.utils;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.lzrc.EmailProject.db.AbstractEmail;
import com.lzrc.EmailProject.db.AbstractEmailErrors;
import com.lzrc.EmailProject.db.Account;
import com.lzrc.EmailProject.db.Email;
import com.lzrc.EmailProject.db.embeddables.EmailEmbeddable;
import com.lzrc.EmailProject.db.entity.factory.EntityEmailErrorsFactory;
import com.lzrc.EmailProject.db.entity.factory.EntityEmailFactory;
import com.lzrc.EmailProject.db.pattern.repositories.EmailPatternMethods;
import com.lzrc.EmailProject.db.repositories.AccountRepository;
import com.lzrc.EmailProject.email.DTO.TypeEmailErrorDTO;

@Component
public class EmailErrorsManager <EMAIL extends AbstractEmail<ERROR> ,ERROR extends AbstractEmailErrors<EMAIL>> {
	
	Class emailClass;
	Class emailErrorClass;
	
	EmailPatternMethods<EMAIL> emailPatternMethods;
	JpaRepository<EMAIL, Long> emailRepository;
	JpaRepository<ERROR, Long> emailErrorsRepository;
	
	private EmailErrorsManager() {}
	
	public EmailErrorsManager(Class emailClass, Class emailErrorClass,
			JpaRepository<EMAIL, Long> emailRepository,
			JpaRepository<ERROR, Long> emailErrorsRepository,
			EmailPatternMethods<EMAIL> emailPatternMethods) {
		
		this.emailClass=emailClass;
		this.emailErrorClass=emailErrorClass;
		
		this.emailRepository=emailRepository;
		this.emailErrorsRepository=emailErrorsRepository;
		this.emailPatternMethods=emailPatternMethods;
		
	}
	
	
	public void saveEmailErrors(List<TypeEmailErrorDTO<Account>> typeEmailErrorDTOs,
			String subject, String emailModelName) {
		
		typeEmailErrorDTOs.forEach(typeEmailErrorDTO -> {
			
			EmailEmbeddable emailEmbeddable= new EmailEmbeddable(emailModelName, (Account)typeEmailErrorDTO.getAccount());
			EMAIL findEmail= (EMAIL)emailPatternMethods.findIdByEmailEmbeddable(emailEmbeddable);
			
			EmailsTemplates emailsTemplates=new EmailsTemplates();
			
			if(findEmail==null) {
				System.out.println("NULL RETURN");
				EntityEmailFactory<EMAIL> emailFactory=new EntityEmailFactory();
				findEmail= emailFactory.emailFactory(this.emailClass);
				
				findEmail.setSubject(subject);
				findEmail.setEmailEmbeddable(emailEmbeddable);
				findEmail=emailRepository.save(findEmail);
			}
			
			EntityEmailErrorsFactory<ERROR> emailErrorsFactory=
					new EntityEmailErrorsFactory<>();
			
			ERROR emailError=emailErrorsFactory.emailErrorFactory(emailErrorClass);
			
			emailError.setData(LocalDateTime.now());
			emailError.setCause(typeEmailErrorDTO.getEmailError());
			emailError.setEmail(findEmail);
			
			emailErrorsRepository.save(emailError);
			});
			
	}
}

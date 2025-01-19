package com.lzrc.EmailProject.emails.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lzrc.EmailProject.db.Account;
import com.lzrc.EmailProject.db.repositories.AccountRepository;
import com.lzrc.EmailProject.email.DTO.TypeEmailErrorDTO;
import com.lzrc.EmailProject.emails.utils.EmailErrorsManager;
import com.lzrc.EmailProject.emails.utils.SendEmail;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EmailsManager {
	
	EmailErrorsManager emailErrorsManager;
	
	AccountRepository accountRepository;
	
	SendEmail sendEmail;
	
	public int sendEmails(List<Account> accounts, String emailModelName, String subject) {
		List<TypeEmailErrorDTO<Account>> typeEmailError= this.sendEmail.sendEmails(accounts, emailModelName, subject);
		this.emailErrorsManager.saveEmailErrors(typeEmailError, subject, emailModelName);
		return typeEmailError.size();
	}
	
}

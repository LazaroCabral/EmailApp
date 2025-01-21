package com.lzrc.emailproject.emails.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lzrc.emailproject.db.Account;
import com.lzrc.emailproject.db.repositories.AccountRepository;
import com.lzrc.emailproject.email.DTO.TypeEmailErrorDTO;
import com.lzrc.emailproject.emails.utils.EmailErrorsManager;
import com.lzrc.emailproject.emails.utils.SendEmail;

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

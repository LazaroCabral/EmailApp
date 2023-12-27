package com.lzrc.EmailProject.emails.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.internal.util.MutableInteger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.lzrc.EmailProject.REST.DTO.SendNowPOSTDTORequest;
import com.lzrc.EmailProject.REST.DTO.SendNowPOSTDTOResponse;
import com.lzrc.EmailProject.db.AbstractEmail;
import com.lzrc.EmailProject.db.AbstractEmailErrors;
import com.lzrc.EmailProject.db.Account;
import com.lzrc.EmailProject.db.Email;
import com.lzrc.EmailProject.db.EmailErrors;
import com.lzrc.EmailProject.db.repositories.AccountRepository;
import com.lzrc.EmailProject.email.DTO.TypeEmailErrorDTO;
import com.lzrc.EmailProject.emails.utils.EmailErrorsManager;
import com.lzrc.EmailProject.emails.utils.SendEmail;

import ch.qos.logback.core.util.COWArrayList;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EmailsManager {
	
	EmailErrorsManager emailErrorsManager;
	
	AccountRepository accountRepository;
	
	SendEmail sendEmail;
	
	public SendNowPOSTDTOResponse sendManuallyEmails(SendNowPOSTDTORequest sendNowPOSTDTORequest) {
		List<String> cpfAccounts=sendNowPOSTDTORequest.getSelectedAccounts();
		boolean selectedAccountsNOT=sendNowPOSTDTORequest.isSelectedAccountsNOT();
		boolean selectAll=sendNowPOSTDTORequest.isSelectAll();
		String emailTemplate=sendNowPOSTDTORequest.getEmailTemplate();
		String subject=sendNowPOSTDTORequest.getSubject();

		List<Account> accounts=new ArrayList();
		
		if(selectAll) {
			List<Account> allAccounts=accountRepository.findAll();
			accounts.addAll(allAccounts);
		}
		else if(selectedAccountsNOT) {
			Set<String> excludeAccounts=new HashSet(cpfAccounts);
			List<Account> dbAccounts= accountRepository.findAll();
			
			dbAccounts.forEach(dbAccount ->{
				boolean contain=excludeAccounts.contains(dbAccount.getCpf());
				if(contain == false) { 
					accounts.add(dbAccount);
				}
			});
			
		} else {
			cpfAccounts.forEach(cpfAccount ->{
				accounts.add(accountRepository.findById(cpfAccount).get());
			});
		}
		
		List<TypeEmailErrorDTO<Account>> emailSendFails=
				this.sendEmail.sendEmails(accounts, emailTemplate, subject);
		
		emailErrorsManager.saveEmailErrors(emailSendFails, subject, emailTemplate);
		
		return new SendNowPOSTDTOResponse(emailSendFails.size(), accounts.size());
	}
	
}

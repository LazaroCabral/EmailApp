package com.lzrc.EmailProject.REST.controllers;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lzrc.EmailProject.REST.DTO.ContaDTO;
import com.lzrc.EmailProject.REST.DTO.SendNowDTO;
import com.lzrc.EmailProject.REST.DTO.SendNowPOSTDTORequest;
import com.lzrc.EmailProject.REST.DTO.SendNowPOSTDTOResponse;
import com.lzrc.EmailProject.db.Account;
import com.lzrc.EmailProject.db.ManuallyEmail;
import com.lzrc.EmailProject.db.ManuallyEmailErrors;
import com.lzrc.EmailProject.db.custom.repositories.CustomAccountRepository;
import com.lzrc.EmailProject.db.custom.repositories.CustomManuallyEmailRepository;
import com.lzrc.EmailProject.db.repositories.AccountRepository;
import com.lzrc.EmailProject.db.repositories.ManuallyEmailErrorsRepository;
import com.lzrc.EmailProject.emails.service.EmailsManager;
import com.lzrc.EmailProject.emails.utils.EmailErrorsManager;
import com.lzrc.EmailProject.emails.utils.EmailsTemplates;
import com.lzrc.EmailProject.emails.utils.SendEmail;

@RestController
@RequestMapping("/rest")
public class HomeRESTController {
	
	@Autowired
	public HomeRESTController(AccountRepository accountRepository, SendEmail sendEmail,
			CustomManuallyEmailRepository customManuallyEmailRepository,
	ManuallyEmailErrorsRepository manuallyEmailErrorsRepository) {
		
		EmailErrorsManager<ManuallyEmail, ManuallyEmailErrors> emailErrorsManager=
				new EmailErrorsManager(ManuallyEmail.class, ManuallyEmailErrors.class,
						customManuallyEmailRepository, 
						manuallyEmailErrorsRepository,
						customManuallyEmailRepository);
		
		this.emailsManager=new EmailsManager(emailErrorsManager, accountRepository, sendEmail);
	
	}

	@Autowired
	CustomAccountRepository customAccountRepository;
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	EmailsTemplates emailsTemplates;
	
	EmailsManager emailsManager;
	
	@GetMapping("/sendnow")
	public SendNowDTO getSendEmails(Pageable page) {
		Page<Account> accounts=accountRepository.findAll(page);
		Iterator<Account> contasIterator=accounts.get().iterator();
		List<ContaDTO> contasDTO=ContaDTO.convertContaToContaDTO(contasIterator);
		return new SendNowDTO(contasDTO, emailsTemplates.getEmailsTemplates(),accounts.getTotalPages(),accounts.getNumber());
	}
	
	@PostMapping("/sendnow")
	public ResponseEntity<SendNowPOSTDTOResponse> postSendEmails(@RequestBody SendNowPOSTDTORequest sendNowPOSTDTORequest) {
		
		SendNowPOSTDTOResponse sendNowPOSTDTOResponse=this.emailsManager.sendManuallyEmails(sendNowPOSTDTORequest);
		
		return ResponseEntity.ok(sendNowPOSTDTOResponse);
	}
	
}

package com.lzrc.EmailProject.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lzrc.EmailProject.DTO.NewUser;
import com.lzrc.EmailProject.db.Account;
import com.lzrc.EmailProject.db.Email;
import com.lzrc.EmailProject.db.custom.repositories.CustomAccountRepository;
import com.lzrc.EmailProject.db.embeddables.EmailEmbeddable;

import jakarta.transaction.Transactional;

@Controller
public class UsersController {
	
	@Value("${default_email_model}")
	private String defaultEmailModel;
	
	@Value("${default_email_subject}")
	private String defaultEmailSubject;
	
	@Value("${spring.mail.username}")
	private String emailOwner;
	
	@Autowired
	CustomAccountRepository customAccountRepository;
	
	@GetMapping("/createuser")
	public ModelAndView getCreateUser() {
		var model=new ModelAndView("create_user");
		model.addObject("NewUser", new NewUser());
		return model;
	}
	
	@Transactional
	@PostMapping("/createuser")
	public ModelAndView postCreateUser(NewUser newUser) {
		var model=new ModelAndView("create_user");
		Account account=new Account(newUser.getCpf(), newUser.getName(), newUser.getEmail());
		System.out.println("default subject ---->"+defaultEmailSubject);
		Email email=new Email(new EmailEmbeddable(defaultEmailModel,account),defaultEmailSubject);
		List<Email> emails=new ArrayList<>();
		emails.add(email);
		account.setCustomAutomatizedEmails(emails);
		customAccountRepository.save(account);
		model.addObject("NewUser", new NewUser());
		return model;
	}
	
	public ModelAndView usersList(Page<Account> page) {
		
		return null;
	}
}

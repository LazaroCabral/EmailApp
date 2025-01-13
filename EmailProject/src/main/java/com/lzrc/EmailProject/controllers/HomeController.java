package com.lzrc.EmailProject.controllers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.apache.logging.log4j.message.SimpleMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lzrc.EmailProject.DTO.EmailDTO;
import com.lzrc.EmailProject.db.Account;
import com.lzrc.EmailProject.db.custom.repositories.CustomAccountRepository;
import com.lzrc.EmailProject.email.DTO.TypeEmailErrorDTO;
import com.lzrc.EmailProject.emails.utils.EmailsTemplates;
import com.lzrc.EmailProject.emails.utils.SendEmail;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

@Controller
public class HomeController {
	
	@Autowired
	CustomAccountRepository customAccountRepository;

	@Autowired
	JavaMailSender javaMailSender;
	
	@Autowired
	SendEmail sendEmail;
	
	@Autowired
	EmailsTemplates emailsTemplates;
	
	@GetMapping("/editmodel")
	public ModelAndView getInsertEmails() {
		ModelAndView mv=new ModelAndView("email_model");
		String model="";
		try {
			model=emailsTemplates.getEmailTemplate(sendEmail.getDefaultEmailModel());
		} catch (IOException e) {
			e.printStackTrace();
		}
		EmailDTO emailDTO=new EmailDTO();
		emailDTO.setMessage(model);
		mv.addObject("EmailModel", emailDTO);
		mv.addObject("model", model);
		return mv;
	}
	
	@PostMapping("/editmodel")
	public ModelAndView postInsertEmails(EmailDTO emailDTO) {
		try {
			this.emailsTemplates.editEmailTemplate(sendEmail.getDefaultEmailModel(), emailDTO.getMessage());
			this.emailsTemplates.getEmailTemplate(sendEmail.getDefaultEmailModel());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return new ModelAndView("redirect:/editmodel");
	}
	
	@GetMapping("/sendnow")
	public ModelAndView sendEmails(ModelAndView modelAndView) {
		modelAndView.setViewName("send_Email");
//		AccountsDTORequest accountsDTORequest=new AccountsDTORequest(customAccountRepository.findAll(), 0, false, this.emailsTemplates.getEmailsTemplates(), sendEmail.getDefaultEmailSubject());
//		
//		modelAndView.addObject("ContasDTORequest", accountsDTORequest);
		return modelAndView;
	}
	
}

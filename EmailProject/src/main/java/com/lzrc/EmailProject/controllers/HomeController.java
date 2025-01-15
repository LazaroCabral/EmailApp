package com.lzrc.EmailProject.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lzrc.EmailProject.DTO.EmailDTO;
import com.lzrc.EmailProject.db.custom.repositories.CustomAccountRepository;
import com.lzrc.EmailProject.emails.utils.EmailsTemplates;
import com.lzrc.EmailProject.emails.utils.SendEmail;

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
		modelAndView.setViewName("send_email");
//		AccountsDTORequest accountsDTORequest=new AccountsDTORequest(customAccountRepository.findAll(), 0, false, this.emailsTemplates.getEmailsTemplates(), sendEmail.getDefaultEmailSubject());
//		
//		modelAndView.addObject("ContasDTORequest", accountsDTORequest);
		return modelAndView;
	}
	
}

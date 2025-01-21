package com.lzrc.emailproject.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lzrc.emailproject.DTO.EmailDTO;
import com.lzrc.emailproject.db.custom.repositories.CustomAccountRepository;
import com.lzrc.emailproject.emails.utils.EmailsTemplates;
import com.lzrc.emailproject.emails.utils.SendEmail;

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
		return modelAndView;
	}
	
}

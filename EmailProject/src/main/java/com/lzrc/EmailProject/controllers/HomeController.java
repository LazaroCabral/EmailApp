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

import com.lzrc.EmailProject.DTO.ContasDTORequest;
import com.lzrc.EmailProject.DTO.EmailDTO;
import com.lzrc.EmailProject.DTO.NewUser;
import com.lzrc.EmailProject.DTO.PostEmail;
import com.lzrc.EmailProject.db.Conta;
import com.lzrc.EmailProject.db.custom.repositories.CustomContaRepository;
import com.lzrc.EmailProject.emails.service.EmailsTemplates;
import com.lzrc.EmailProject.emails.service.SendEmail;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

@Controller
public class HomeController {
	
	@Value("${default_email_model}")
	String defaultEmailModel;
	
	@Value("${default_email_subject}")
	String defaultEmailSubject;
	
	@Autowired
	CustomContaRepository customContaRepository;

	@Autowired
	JavaMailSender javaMailSender;
	
	@Autowired
	SendEmail sendEmail;
	
	@Autowired
	EmailsTemplates emailModel;
	
	@GetMapping("/editmodel")
	public ModelAndView getInsertEmails() {
		ModelAndView mv=new ModelAndView("email_model");
		String model="";
		try {
			model=emailModel.getEmailTemplate(defaultEmailModel);
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
			this.emailModel.editEmailTemplate(defaultEmailModel, emailDTO.getMessage());
			this.emailModel.getEmailTemplate(defaultEmailModel);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return new ModelAndView("redirect:/editmodel");
	}
	
	@GetMapping("/sendnow")
	public ModelAndView sendEmails(ModelAndView modelAndView) {
		modelAndView.setViewName("send_Email");
		ContasDTORequest contasDTORequest=new ContasDTORequest(customContaRepository.findAll(), 0, false, this.emailModel.getEmailsTemplates(), defaultEmailSubject);
		
		modelAndView.addObject("ContasDTORequest", contasDTORequest);
		return modelAndView;
	}
	
	@PostMapping("/sendnow")
	public ModelAndView sendEmails(HttpSession httpSession,
			ContasDTORequest contasDTORequest, RedirectAttributes redirectAttributes, ModelAndView modelAndView) {
		modelAndView.setViewName("redirect:sendnow");
		
		List<Conta> contas=new ArrayList();
		try {
			for (Conta conta : contasDTORequest.getContas()) {
				
				Optional<Conta> c=customContaRepository.findById(conta.getCpf());
				contas.add(c.get());
			}
			
			int emailFails=sendEmail.sendEmails(contas, contasDTORequest.getEmailsModel()[0], contasDTORequest.getSubject());
			redirectAttributes.addFlashAttribute("totalSelectedAccounts", contasDTORequest.getContas().size());
			redirectAttributes.addFlashAttribute("sendEmailFails", emailFails);
			
		} catch (IOException e) {
			System.out.println("-------------email error-------------");
			redirectAttributes.addFlashAttribute("findEmailModelError", "Modelo de email não encontrado!!");
			e.printStackTrace();
		}

		return modelAndView;
	}
	
}

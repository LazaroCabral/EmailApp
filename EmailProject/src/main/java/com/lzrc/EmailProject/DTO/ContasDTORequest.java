package com.lzrc.EmailProject.DTO;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.lzrc.EmailProject.db.Conta;
import com.lzrc.EmailProject.emails.service.EmailsTemplates;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class ContasDTORequest {
	private List<Conta> contas;
	private int totalPages;
	private boolean send;
	private String[] emailsModel;
	private String subject;
	
	public ContasDTORequest() {}
	
	public ContasDTORequest(List<Conta> contas, int totalPages, boolean send, String[] emailsModel, String subject) {
		super();
		this.contas = contas;
		this.totalPages = totalPages;
		this.send = send;
		this.emailsModel = emailsModel;
		this.subject=subject;
	}
	

}

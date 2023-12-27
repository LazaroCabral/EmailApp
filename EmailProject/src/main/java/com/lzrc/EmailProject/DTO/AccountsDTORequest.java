package com.lzrc.EmailProject.dto;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.lzrc.EmailProject.db.Account;
import com.lzrc.EmailProject.emails.utils.EmailsTemplates;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class AccountsDTORequest {
	private List<Account> accounts;
	private int totalPages;
	private boolean send;
	private String[] emailsModel;
	private String subject;
	
	public AccountsDTORequest() {}
	
	public AccountsDTORequest(List<Account> accounts, int totalPages, boolean send, String[] emailsModel, String subject) {
		super();
		this.accounts = accounts;
		this.totalPages = totalPages;
		this.send = send;
		this.emailsModel = emailsModel;
		this.subject=subject;
	}
	

}

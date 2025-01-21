package com.lzrc.emailproject.DTO;

import java.util.List;
import org.springframework.stereotype.Component;

import com.lzrc.emailproject.db.Account;

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

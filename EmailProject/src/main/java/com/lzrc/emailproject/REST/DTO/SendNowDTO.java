package com.lzrc.emailproject.REST.DTO;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendNowDTO {
	
	private List<ContaDTO> Accounts;
	private String[] emailTemplates;
	private String subject="Assunto";
	private int totalPages=0;
	private int currentPage=0;
	
	public SendNowDTO() {}

	public SendNowDTO(List<ContaDTO> accounts, String[] emailTemplates, int totalPages, int currentPage) {
		Accounts = accounts;
		this.emailTemplates = emailTemplates;
		this.totalPages = totalPages;
		this.currentPage = currentPage;
	}

	public SendNowDTO(List<ContaDTO> accounts, String[] emailTemplates, String subject, int totalPages,
			int currentPage) {
		Accounts = accounts;
		this.emailTemplates = emailTemplates;
		this.subject = subject;
		this.totalPages = totalPages;
		this.currentPage = currentPage;
	}

}

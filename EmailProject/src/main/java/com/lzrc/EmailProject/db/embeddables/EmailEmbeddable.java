package com.lzrc.EmailProject.db.embeddables;

import com.lzrc.EmailProject.db.Account;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Embeddable
@ToString
@EqualsAndHashCode
public class EmailEmbeddable {
	
	private String emailModelName;
	@ManyToOne
	private Account account;
	
	public EmailEmbeddable() {}
	
	public EmailEmbeddable(String modelMessage, Account account) {
		this.emailModelName=modelMessage;
		this.account=account;
	}
	
	public EmailEmbeddable(Account account) {
		this.account=account;
	}
}

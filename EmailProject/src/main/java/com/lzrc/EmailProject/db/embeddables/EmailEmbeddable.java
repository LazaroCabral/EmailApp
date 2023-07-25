package com.lzrc.EmailProject.db.embeddables;

import com.lzrc.EmailProject.db.Conta;

import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class EmailEmbeddable {
	
	private String emailModelName;
	@ManyToOne
	private Conta conta;
	
	public EmailEmbeddable() {}
	
	public EmailEmbeddable(String modelMessage, Conta conta) {
		this.emailModelName=modelMessage;
		this.conta=conta;
	}
	
	public EmailEmbeddable(Conta conta) {
		this.conta=conta;
	}
}

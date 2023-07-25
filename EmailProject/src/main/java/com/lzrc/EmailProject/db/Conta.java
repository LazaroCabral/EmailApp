package com.lzrc.EmailProject.db;

import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Conta {
	
	@Id
	private String cpf;
	private String name;
	private String email;
	
	@OneToMany(mappedBy = "emailEmbeddable.conta", cascade = CascadeType.ALL)
	private List<Email> customAutomatizedEmails;
	
	
	public Conta() {}
	
	public Conta(String name, String email, List<Email> customAutomatizedEmails) {
		this.name=name;
		this.email=email;
		this.customAutomatizedEmails=customAutomatizedEmails;
	}
	
	public Conta(String name, String email) {
		this.name=name;
		this.email=email;
	}
	
	public Conta(String cpf, String name, String email) {
		this.cpf = cpf;
		this.name = name;
		this.email = email;
		
	}	
	
}

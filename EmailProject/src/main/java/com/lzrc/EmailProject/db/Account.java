package com.lzrc.EmailProject.db;

import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
public class Account {
	
	@Id
	private String cpf;
	private String name;
	private String email;
	
	@OneToMany(mappedBy = "emailEmbeddable.account", cascade = CascadeType.ALL)
	private List<Email> customAutomatizedEmails;
	
	
	public Account() {}
	
	public Account(String name, String email, List<Email> customAutomatizedEmails) {
		this.name=name;
		this.email=email;
		this.customAutomatizedEmails=customAutomatizedEmails;
	}
	
	public Account(String name, String email) {
		this.name=name;
		this.email=email;
	}
	
	public Account(String cpf, String name, String email) {
		this.cpf = cpf;
		this.name = name;
		this.email = email;
		
	}	
	
}

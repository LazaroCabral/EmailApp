package com.lzrc.EmailProject.db;

import java.util.List;

import com.lzrc.EmailProject.db.embeddables.EmailEmbeddable;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class AbstractEmail<T> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true)
	private EmailEmbeddable emailEmbeddable=new EmailEmbeddable();
	private String subject;
		
	@OneToMany(mappedBy = "email")
	private List<T> emailErrors;
	
	public AbstractEmail() {}
	
	public AbstractEmail(String emailModelName, String subject) {
		this.getEmailEmbeddable().setEmailModelName(emailModelName);
		this.setSubject(subject);
	}

	public AbstractEmail(String emailModelName, Conta conta, String subject) {
		this.emailEmbeddable.setEmailModelName(emailModelName);
		this.emailEmbeddable.setConta(conta);
		this.subject=subject;
	}
	
	public AbstractEmail(EmailEmbeddable emailEmbeddable, String subject) {
		this.emailEmbeddable=emailEmbeddable;
		this.subject=subject;
	}
	
	public static void p() {
		
	}

}

package com.lzrc.EmailProject.db;

import java.time.LocalDateTime;
import java.util.List;

import com.lzrc.EmailProject.db.embeddables.EmailEmbeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Email extends AbstractEmail<EmailErrors>{

	public Email() {
		super();
	}

	public Email(EmailEmbeddable emailEmbeddable, String subject) {
		super(emailEmbeddable, subject);
	}

	public Email(String emailModelName, Conta conta, String subject) {
		super(emailModelName, conta, subject);
	}

	public Email(String emailModelName, String subject) {
		super(emailModelName, subject);
	}

}

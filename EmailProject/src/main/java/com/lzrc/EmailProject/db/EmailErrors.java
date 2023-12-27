package com.lzrc.EmailProject.db;

import java.time.LocalDateTime;

import com.lzrc.EmailProject.email.DTO.EmailError;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@EqualsAndHashCode
public class EmailErrors extends AbstractEmailErrors<Email>{

	public EmailErrors() {
		super();
	}

	public EmailErrors(LocalDateTime data, EmailError cause, Email email) {
		super(data, cause, email);
	}

}

package com.lzrc.EmailProject.db;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class EmailErrors extends AbstractEmailErrors<Email>{

	public EmailErrors() {
		super();
	}

	public EmailErrors(LocalDateTime data, String cause, Email email) {
		super(data, cause, email);
	}

}

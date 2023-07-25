package com.lzrc.EmailProject.db;

import java.util.List;

import com.lzrc.EmailProject.db.embeddables.EmailEmbeddable;
import com.lzrc.EmailProject.db.repositories.ManuallyEmailErrorsRepository;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ManuallyEmail extends AbstractEmail<ManuallyEmailErrors>{

	public ManuallyEmail() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ManuallyEmail(String emailModelName, Conta conta, String subject) {
		super(emailModelName, conta, subject);
		// TODO Auto-generated constructor stub
	}

	public ManuallyEmail(String emailModelName, String subject) {
		super(emailModelName, subject);
		// TODO Auto-generated constructor stub
	}

}

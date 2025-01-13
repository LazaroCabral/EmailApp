package com.lzrc.EmailProject.db;

import jakarta.persistence.Entity;
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

	public ManuallyEmail(String emailModelName, Account account, String subject) {
		super(emailModelName, account, subject);
		// TODO Auto-generated constructor stub
	}

	public ManuallyEmail(String emailModelName, String subject) {
		super(emailModelName, subject);
		// TODO Auto-generated constructor stub
	}

}

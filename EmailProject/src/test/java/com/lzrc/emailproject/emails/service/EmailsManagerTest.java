package com.lzrc.emailproject.emails.service;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mockitoSession;
import static org.mockito.Mockito.times;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.stubbing.answers.AnswersWithDelay;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.repository.JpaRepository;

import com.lzrc.emailproject.db.AbstractEmail;
import com.lzrc.emailproject.db.AbstractEmailErrors;
import com.lzrc.emailproject.db.Account;
import com.lzrc.emailproject.db.Email;
import com.lzrc.emailproject.db.EmailErrors;
import com.lzrc.emailproject.db.embeddables.EmailEmbeddable;
import com.lzrc.emailproject.db.pattern.repositories.EmailPatternMethods;
import com.lzrc.emailproject.db.repositories.AccountRepository;
import com.lzrc.emailproject.email.DTO.EmailError;
import com.lzrc.emailproject.email.DTO.TypeEmailErrorDTO;
import com.lzrc.emailproject.emails.utils.EmailErrorsManager;

@ExtendWith(MockitoExtension.class)
public class EmailsManagerTest {
	
	public EmailsManagerTest() {
		
		this.emailClass=Email.class;
		this.emailErrorsClass=EmailErrors.class;
		
	}
	
	List<TypeEmailErrorDTO<Account>> typeEmailErrorDTOs;
	
	List<Account> accounts;
	
	List<Email> emails;
	
	List<EmailErrors> emailErrors;
	
	Class emailClass;
	Class emailErrorsClass;
	
	@Mock
	JpaRepository<Email, Long> emailRepository;
	
	@Mock
	JpaRepository<EmailErrors, Long> emailErrorsRepository;
	
	@Mock
	EmailPatternMethods<Email> emailPatternMethods;
	
	@Mock
	AccountRepository accountRepository;
	
	@BeforeEach
	@DisplayName("Inialize the data of tables of DB in memory and the necessary objects")
	public void initDBTables() {
		this.accounts=new ArrayList();
		accounts.add(new Account("111-111-111-11","lazaro","lazaro@Ngmail.com"));
		accounts.add(new Account("222-222-222-22","joao","joao@Ngmail.com"));
		accounts.add(new Account("333-333-333-33","pedro","pedro@Ngmail.com"));
		
		this.emails=new ArrayList();
		emails.add(new Email(1L,"text",accounts.get(0),"test",new ArrayList<EmailErrors>()));
		emails.add(new Email(2L,"text",accounts.get(1),"test",new ArrayList<EmailErrors>()));
		emails.add(new Email(3L,"text",accounts.get(2),"test",new ArrayList<EmailErrors>()));
		
		this.emailErrors=new ArrayList();
		emailErrors.add(new EmailErrors(null,EmailError.SEND_FAILURE,
				emails.get(0)));
		emailErrors.add(new EmailErrors(null,EmailError.SEND_FAILURE,
				emails.get(1)));
		emailErrors.add(new EmailErrors(null,EmailError.SEND_FAILURE,
				emails.get(2)));
		
		this.typeEmailErrorDTOs=new ArrayList();
		accounts.forEach(account -> {
			typeEmailErrorDTOs.add(new TypeEmailErrorDTO<Account>(account, EmailError.SEND_FAILURE));
		});
		
	}
	
	@Test
	@DisplayName("Test if the method saveEmailErrors is save all the e-mail errors")
	public void saveEmailsErrorsTest() {
		
		emailErrors.forEach(emailError -> {
			Mockito.when(this.emailPatternMethods.findIdByEmailEmbeddable(
					eq(emailError.getEmail().getEmailEmbeddable())
					)).thenReturn(emailError.getEmail());
			
		});
		
		EmailErrorsManager<Email, EmailErrors> emailsManager=new EmailErrorsManager(this.emailClass, this.emailErrorsClass,
				this.emailRepository, this.emailErrorsRepository, this.emailPatternMethods
				);
		
		emailsManager.saveEmailErrors(this.typeEmailErrorDTOs, emails.get(0).getSubject(), 
				emails.get(0).getEmailEmbeddable().getEmailModelName());

		Mockito.verify(emailErrorsRepository, times(typeEmailErrorDTOs.size()))
			.save(Mockito.any());
	}
}

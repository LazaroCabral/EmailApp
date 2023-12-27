package com.lzrc.EmailTest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.lzrc.EmailProject.db.Email;
import com.lzrc.EmailProject.db.custom.repositories.CustomEmailRepository;
import com.lzrc.EmailProject.db.entity.factory.EntityEmailErrorsFactory;
import com.lzrc.EmailProject.db.entity.factory.EntityEmailFactory;
import com.lzrc.EmailProject.emails.utils.EmailsTemplates;
import com.lzrc.EmailProject.emails.utils.SendEmail;

//@ActiveProfiles("test")
@SpringBootTest(classes = EmailProjectApplicationTests.class)
//@ExtendWith(SpringExtension.class)
class EmailProjectApplicationTests {

//	@Autowired
//	AccountRepository accountRepository;
//	
//	@InjectMocks
//	CustomEmailRepository customEmailRepository;
//	
//	@Autowired
//	CustomEmailRepository emailRepository;
//	
//	@Autowired
//	CustomAccountRepository customAccountRepository;

	String s="C:\\Users\\lazaro\\Documents\\modelos_email\\";

	@Test
	void contextLoads() {

		EntityEmailFactory<Email> factory=new EntityEmailFactory<>();
		Email obj=factory.emailFactory(Email.class);
		System.out.println(obj);
		System.out.println("----------------init------------");
//		customEmailRepository.findAll();
//		//System.out.println("test ------->"+customEmailRepository.findDistinctByEmailModelName().get(0));
//		System.out.println("-------------------end-----------------");
	}

}

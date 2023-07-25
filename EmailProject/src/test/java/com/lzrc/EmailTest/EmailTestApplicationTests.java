package com.lzrc.EmailTest;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.lzrc.EmailProject.db.Conta;
import com.lzrc.EmailProject.db.custom.repositories.CustomContaRepository;
import com.lzrc.EmailProject.db.custom.repositories.CustomEmailRepository;
import com.lzrc.EmailProject.db.repositories.ContaRepository;

@SpringBootTest
class EmailTestApplicationTests {

	@Autowired
	ContaRepository contaRepository;
	
	@Autowired
	CustomEmailRepository customEmailRepository;
	
	@Autowired
	CustomContaRepository customContaRepository;
	
	@Test
	void contextLoads() {
		System.out.println("----------------init------------");

		System.out.println("test ------->"+customEmailRepository.findAllDistinctModelEmails().get(0));
		System.out.println("-------------------end-----------------");
	}

}

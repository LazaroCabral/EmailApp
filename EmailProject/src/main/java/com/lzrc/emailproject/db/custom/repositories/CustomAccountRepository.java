package com.lzrc.emailproject.db.custom.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lzrc.emailproject.db.Account;
import com.lzrc.emailproject.db.repositories.AccountRepository;

@Repository
public interface CustomAccountRepository extends AccountRepository {
	
	@Query("SELECT a FROM Account a JOIN FETCH a.customAutomatizedEmails e WHERE a=e.emailEmbeddable.account")
	List<Account> findAllJoinEmails();
		
	@Query("SELECT a FROM Account a JOIN FETCH a.customAutomatizedEmails e WHERE a.cpf=e.emailEmbeddable.account.cpf AND e.emailEmbeddable.emailModelName = :emailModel")
	List<Account> findContasWithEmails(String emailModel);
	
}

package com.lzrc.EmailProject.db.custom.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.lzrc.EmailProject.db.Conta;
import com.lzrc.EmailProject.db.Email;
import com.lzrc.EmailProject.db.repositories.ContaRepository;

@Service
public interface CustomContaRepository extends ContaRepository {
	
	@Query("SELECT c FROM Conta c JOIN FETCH c.customAutomatizedEmails e WHERE c=e.emailEmbeddable.conta")
	List<Conta> findAllJoinEmails();
	
	@Query(value="SELECT c FROM Conta c JOIN Email e where c.cpf=e.emailEmbeddable.conta.cpf")
	List<Conta> dt();
		
	@Query("SELECT c FROM Conta c JOIN FETCH c.customAutomatizedEmails e WHERE c.cpf=e.emailEmbeddable.conta.cpf AND e.emailEmbeddable.emailModelName = :emailModel")
	List<Conta> findContasWithEmails(String emailModel);
	
}

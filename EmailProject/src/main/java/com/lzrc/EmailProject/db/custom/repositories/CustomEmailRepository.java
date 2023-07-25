package com.lzrc.EmailProject.db.custom.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.lzrc.EmailProject.db.Conta;
import com.lzrc.EmailProject.db.Email;
import com.lzrc.EmailProject.db.embeddables.EmailEmbeddable;
import com.lzrc.EmailProject.db.repositories.EmailRepository;

public interface CustomEmailRepository extends EmailRepository {
	
	@Query("select distinct new Email(e.emailEmbeddable.emailModelName, e.subject) from Email e")
	List<Email> findAllDistinctModelEmails();
	
	Email findIdByEmailEmbeddable(EmailEmbeddable emailEmbeddable);


}

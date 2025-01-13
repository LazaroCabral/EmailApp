package com.lzrc.EmailProject.db.custom.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lzrc.EmailProject.db.Email;
import com.lzrc.EmailProject.db.pattern.repositories.EmailPatternMethods;
import com.lzrc.EmailProject.db.repositories.EmailRepository;

@Repository
public interface CustomEmailRepository extends EmailRepository, EmailPatternMethods<Email> {
	
	@Query("select distinct new Email(e.emailEmbeddable.emailModelName, e.subject) from Email e")
	List<Email> findAllDistinctModelEmails();

}

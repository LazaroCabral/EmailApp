package com.lzrc.EmailProject.db.custom.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lzrc.EmailProject.db.Email;
import com.lzrc.EmailProject.db.ManuallyEmail;
import com.lzrc.EmailProject.db.embeddables.EmailEmbeddable;
import com.lzrc.EmailProject.db.pattern.repositories.EmailPatternMethods;
import com.lzrc.EmailProject.db.repositories.EmailRepository;
import com.lzrc.EmailProject.db.repositories.ManuallyEmailRepository;

@Repository
public interface CustomManuallyEmailRepository extends ManuallyEmailRepository, EmailPatternMethods<ManuallyEmail> {

	@Query("select distinct new ManuallyEmail(e.emailEmbeddable.emailModelName, e.subject) from Email e")
	List<Email> findAllDistinctModelEmails();
	
}

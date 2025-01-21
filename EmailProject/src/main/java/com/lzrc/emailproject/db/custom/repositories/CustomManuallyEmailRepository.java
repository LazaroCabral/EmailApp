package com.lzrc.emailproject.db.custom.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lzrc.emailproject.db.Email;
import com.lzrc.emailproject.db.ManuallyEmail;
import com.lzrc.emailproject.db.pattern.repositories.EmailPatternMethods;
import com.lzrc.emailproject.db.repositories.ManuallyEmailRepository;

@Repository
public interface CustomManuallyEmailRepository extends ManuallyEmailRepository, EmailPatternMethods<ManuallyEmail> {

	@Query("select distinct new ManuallyEmail(e.emailEmbeddable.emailModelName, e.subject) from Email e")
	List<Email> findAllDistinctModelEmails();
	
}

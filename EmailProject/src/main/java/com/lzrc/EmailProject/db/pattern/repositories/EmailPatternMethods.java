package com.lzrc.EmailProject.db.pattern.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lzrc.EmailProject.db.AbstractEmail;
import com.lzrc.EmailProject.db.AbstractEmailErrors;
import com.lzrc.EmailProject.db.embeddables.EmailEmbeddable;

public interface EmailPatternMethods<T> {

	T findIdByEmailEmbeddable(EmailEmbeddable emailEmbeddable);
	
}

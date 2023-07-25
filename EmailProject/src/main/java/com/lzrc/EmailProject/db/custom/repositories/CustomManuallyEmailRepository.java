package com.lzrc.EmailProject.db.custom.repositories;

import org.springframework.stereotype.Repository;

import com.lzrc.EmailProject.db.ManuallyEmail;
import com.lzrc.EmailProject.db.embeddables.EmailEmbeddable;
import com.lzrc.EmailProject.db.repositories.EmailRepository;
import com.lzrc.EmailProject.db.repositories.ManuallyEmailRepository;

@Repository
public interface CustomManuallyEmailRepository extends ManuallyEmailRepository {

	ManuallyEmail findIdByEmailEmbeddable(EmailEmbeddable emailEmbeddable);
}

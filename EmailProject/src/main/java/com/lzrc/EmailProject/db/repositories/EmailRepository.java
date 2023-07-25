package com.lzrc.EmailProject.db.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lzrc.EmailProject.db.Email;
import com.lzrc.EmailProject.db.embeddables.EmailEmbeddable;

@Repository
public interface EmailRepository extends JpaRepository<Email, Long> {
	

}

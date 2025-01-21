package com.lzrc.emailproject.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lzrc.emailproject.db.Email;

@Repository
public interface EmailRepository extends JpaRepository<Email, Long> {
	
}

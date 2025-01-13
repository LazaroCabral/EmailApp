package com.lzrc.EmailProject.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.lzrc.EmailProject.db.Email;

@Repository
public interface EmailRepository extends JpaRepository<Email, Long> {
	
}

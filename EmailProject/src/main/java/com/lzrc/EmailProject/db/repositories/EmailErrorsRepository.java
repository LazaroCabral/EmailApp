package com.lzrc.EmailProject.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lzrc.EmailProject.db.EmailErrors;

@Repository
public interface EmailErrorsRepository extends JpaRepository<EmailErrors, Long>{

}

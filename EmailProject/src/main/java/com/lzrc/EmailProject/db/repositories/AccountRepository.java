package com.lzrc.EmailProject.db.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.lzrc.EmailProject.db.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, String>{
	
	
}

package com.lzrc.emailproject.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lzrc.emailproject.db.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, String>{
	
	
}

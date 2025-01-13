package com.lzrc.EmailProject.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.lzrc.EmailProject.db.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, String>{
	
	
}

package com.lzrc.emailproject.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lzrc.emailproject.db.ManuallyEmail;
import com.lzrc.emailproject.db.pattern.repositories.EmailPatternMethods;

@Repository
public interface ManuallyEmailRepository extends JpaRepository<ManuallyEmail, Long>, EmailPatternMethods<ManuallyEmail> {

}

package com.lzrc.EmailProject.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lzrc.EmailProject.db.ManuallyEmail;

@Repository
public interface ManuallyEmailRepository extends JpaRepository<ManuallyEmail, Long> {

}

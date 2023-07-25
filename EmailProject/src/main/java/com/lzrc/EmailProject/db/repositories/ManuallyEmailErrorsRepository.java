package com.lzrc.EmailProject.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lzrc.EmailProject.db.ManuallyEmailErrors;

@Repository
public interface ManuallyEmailErrorsRepository extends JpaRepository<ManuallyEmailErrors, Long>{

}

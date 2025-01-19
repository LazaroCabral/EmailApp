package com.lzrc.EmailProject.emails.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.lzrc.EmailProject.db.Email;
import com.lzrc.EmailProject.db.EmailErrors;
import com.lzrc.EmailProject.db.ManuallyEmail;
import com.lzrc.EmailProject.db.ManuallyEmailErrors;
import com.lzrc.EmailProject.db.custom.repositories.CustomEmailRepository;
import com.lzrc.EmailProject.db.repositories.EmailErrorsRepository;
import com.lzrc.EmailProject.db.repositories.ManuallyEmailErrorsRepository;
import com.lzrc.EmailProject.db.repositories.ManuallyEmailRepository;

@Component
public class EmailErrorManagerBeans {

    @Autowired
    CustomEmailRepository emailRepository;

    @Autowired
    EmailErrorsRepository emailErrorsRepository;

    @Autowired
    ManuallyEmailRepository manuallyEmailRepository;

    @Autowired
    ManuallyEmailErrorsRepository manuallyEmailErrorsRepository;
 
    @Bean(name = "defaultEmailErrorsManager")
    public EmailErrorsManager<Email,EmailErrors> getEmailErrorsManager(){
        return new EmailErrorsManager<>(
            Email.class,
            EmailErrors.class, 
            this.emailRepository, 
            this.emailErrorsRepository, 
            this.emailRepository
            );
    }

    @Bean(name = "manuallyEmailErrorsManager")
    public EmailErrorsManager<ManuallyEmail,ManuallyEmailErrors> getManuallyEmailErrorsManager(){
        return new EmailErrorsManager<>(
            ManuallyEmail.class,
            ManuallyEmailErrors.class, 
            this.manuallyEmailRepository, 
            this.manuallyEmailErrorsRepository, 
            this.manuallyEmailRepository
            );
    }

}

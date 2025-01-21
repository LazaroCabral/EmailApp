package com.lzrc.emailproject.emails.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.lzrc.emailproject.db.Email;
import com.lzrc.emailproject.db.EmailErrors;
import com.lzrc.emailproject.db.ManuallyEmail;
import com.lzrc.emailproject.db.ManuallyEmailErrors;
import com.lzrc.emailproject.db.custom.repositories.CustomEmailRepository;
import com.lzrc.emailproject.db.repositories.EmailErrorsRepository;
import com.lzrc.emailproject.db.repositories.ManuallyEmailErrorsRepository;
import com.lzrc.emailproject.db.repositories.ManuallyEmailRepository;

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

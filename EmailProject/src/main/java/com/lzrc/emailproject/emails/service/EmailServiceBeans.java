package com.lzrc.emailproject.emails.service;

import java.beans.BeanProperty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.lzrc.emailproject.db.Email;
import com.lzrc.emailproject.db.EmailErrors;
import com.lzrc.emailproject.db.ManuallyEmail;
import com.lzrc.emailproject.db.ManuallyEmailErrors;
import com.lzrc.emailproject.db.repositories.AccountRepository;
import com.lzrc.emailproject.emails.utils.EmailErrorsManager;
import com.lzrc.emailproject.emails.utils.SendEmail;

@Component
public class EmailServiceBeans {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    @Qualifier("defaultEmailErrorsManager")
    EmailErrorsManager<Email,EmailErrors> emailErrorsManager;

    @Autowired
    @Qualifier("manuallyEmailErrorsManager")
    EmailErrorsManager<ManuallyEmail,ManuallyEmailErrors> manuallyEmailErrorsManager;

    @Autowired    
    SendEmail sendEmail;

    @Bean(name = "defaultEmailsManager")
    public EmailsManager getEmailsManager(){
        return new EmailsManager(
            this.emailErrorsManager,
            this.accountRepository,
            this.sendEmail);
    }

    @Bean(name = "manuallyEmailsManager")
    public EmailsManager getManuallyEmailsManager(){
        return new EmailsManager(
            this.manuallyEmailErrorsManager,
            this.accountRepository,
            this.sendEmail);
    }
}

package com.lzrc.EmailProject.REST.commands;

import com.lzrc.EmailProject.REST.DTO.SendNowPOSTDTORequest;
import com.lzrc.EmailProject.REST.DTO.SendNowPOSTDTOResponse;
import com.lzrc.EmailProject.emails.service.EmailsManager;

public class SendEmailsCommand {

    private SendNowPOSTDTORequest sendNowPOSTDTORequest;

    private EmailsManager emailsManager;

    public SendEmailsCommand(SendNowPOSTDTORequest sendNowPOSTDTORequest, EmailsManager emailsManager){
        this.sendNowPOSTDTORequest=sendNowPOSTDTORequest;
        this.emailsManager=emailsManager;
    }

    public SendNowPOSTDTOResponse execute(){
        return this.emailsManager.sendManuallyEmails(this.sendNowPOSTDTORequest);
    }

}
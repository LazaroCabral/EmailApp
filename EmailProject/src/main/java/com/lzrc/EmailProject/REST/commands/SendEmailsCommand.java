package com.lzrc.EmailProject.REST.commands;

import java.util.ArrayList;
import java.util.List;

import com.lzrc.EmailProject.REST.DTO.SendNowPOSTDTORequest;
import com.lzrc.EmailProject.REST.DTO.SendNowPOSTDTOResponse;
import com.lzrc.EmailProject.db.Account;
import com.lzrc.EmailProject.db.repositories.AccountRepository;
import com.lzrc.EmailProject.emails.service.EmailsManager;

public class SendEmailsCommand {

    private SendNowPOSTDTORequest sendNowPOSTDTORequest;

    private EmailsManager emailsManager;

    private AccountRepository accountRepository;

    public SendEmailsCommand(SendNowPOSTDTORequest sendNowPOSTDTORequest, EmailsManager emailsManager,
                                AccountRepository accountRepository){
        this.sendNowPOSTDTORequest=sendNowPOSTDTORequest;
        this.emailsManager=emailsManager;
        this.accountRepository=accountRepository;
    }

    private List<Account> getAccounts(){
        List<Account> accounts=new ArrayList<>();

        if(sendNowPOSTDTORequest.isSelectAll()) {
			accounts=accountRepository.findAll();
		} else {
            accounts=accountRepository.findAllById(
                sendNowPOSTDTORequest.getSelectedAccounts());
        }
        return accounts;
    }

    private List<Account> selectedAccounts(){
		boolean selectedAccountsNOT=sendNowPOSTDTORequest.isSelectedAccountsNOT();

        List<Account> accounts=this.getAccounts();
		
        if(selectedAccountsNOT && 
                sendNowPOSTDTORequest.isSelectAll() == false){
            List<Account> selectedAccounts=accountRepository.findAll();
            selectedAccounts.removeAll(accounts);
            return selectedAccounts;
        }

        return accounts;

    }

    public SendNowPOSTDTOResponse execute(){

		String emailTemplate=sendNowPOSTDTORequest.getEmailTemplate();
		String subject=sendNowPOSTDTORequest.getSubject();

        List<Account> accounts=this.selectedAccounts();

        int accountErrors=this.emailsManager.sendEmails(accounts, emailTemplate, subject);
        return new SendNowPOSTDTOResponse(accountErrors, accounts.size());
    }

}
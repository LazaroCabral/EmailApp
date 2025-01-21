package com.lzrc.emailproject.REST.DTO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SendNowPOSTDTORequest {
	List<String> selectedAccounts;
	boolean selectedAccountsNOT; 
	boolean selectAll;
	String emailTemplate;
	String subject;
	
}

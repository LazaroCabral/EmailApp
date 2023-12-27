package com.lzrc.EmailProject.REST.DTO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.lzrc.EmailProject.db.Account;

public record ContaDTO(String cpf, String name, String email) {
	
	public static List<ContaDTO> convertContaToContaDTO(Iterator<Account> contasIterator){
		List<ContaDTO> contasDTO=new ArrayList<>();
		while(contasIterator.hasNext()) {
			Account account=contasIterator.next();
			contasDTO.add(
				new ContaDTO(account.getCpf(), account.getName(), account.getEmail())
			);
		}
		return contasDTO;
	}
	public static List<Account> convertContaDTOToConta(List<ContaDTO> contasDTOList){
		List<Account> accounts=new ArrayList<>();
		for (ContaDTO contaDTO : contasDTOList) {
			
			accounts.add(
				new Account(contaDTO.cpf(), contaDTO.name(), contaDTO.email())
			);
		}
		return accounts;
	}
}

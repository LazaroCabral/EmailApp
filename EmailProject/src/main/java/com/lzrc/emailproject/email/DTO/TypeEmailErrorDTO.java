package com.lzrc.emailproject.email.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TypeEmailErrorDTO <T>{
	
	T account;
	EmailError emailError;

}

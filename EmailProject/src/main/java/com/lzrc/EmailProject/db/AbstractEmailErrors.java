package com.lzrc.EmailProject.db;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class AbstractEmailErrors <T>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDateTime data;
	private String cause;
	
	@ManyToOne
	private T email;
	
	AbstractEmailErrors() {}
	
	public AbstractEmailErrors(LocalDateTime data, String cause, T email) {
		this.setData(data);
		this.setCause(cause);
		this.email = email;
	}


}

package com.lzrc.emailproject.db;

import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import com.lzrc.emailproject.email.DTO.EmailError;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
@EqualsAndHashCode
public abstract class AbstractEmailErrors <T>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@DateTimeFormat
	private LocalDateTime data;

	private EmailError cause;
	
	@ManyToOne
	private T email;
	
	AbstractEmailErrors() {}
	
	public AbstractEmailErrors(LocalDateTime data, EmailError cause, T email) {
		this.setData(data);
		this.setCause(cause);
		this.email = email;
	}


}

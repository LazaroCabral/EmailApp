package com.lzrc.emailproject.db;

import java.time.LocalDateTime;

import com.lzrc.emailproject.email.DTO.EmailError;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

/**
 * @author lazaro
 *
 */

@Entity
@Getter
@Setter
public class ManuallyEmailErrors extends AbstractEmailErrors<ManuallyEmail>{

	public ManuallyEmailErrors() {
		super();
	}

	public ManuallyEmailErrors(LocalDateTime data, EmailError cause, ManuallyEmail email) {
		super(data, cause, email);
	}
	

}

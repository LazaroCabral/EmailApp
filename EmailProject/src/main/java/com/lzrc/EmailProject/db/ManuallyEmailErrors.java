/**
 * 
 */
package com.lzrc.EmailProject.db;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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

	public ManuallyEmailErrors(LocalDateTime data, String cause, ManuallyEmail email) {
		super(data, cause, email);
	}
	

}

package com.lzrc.emailproject.db.pattern.repositories;

import com.lzrc.emailproject.db.embeddables.EmailEmbeddable;

public interface EmailPatternMethods<T> {

	T findIdByEmailEmbeddable(EmailEmbeddable emailEmbeddable);
	
}

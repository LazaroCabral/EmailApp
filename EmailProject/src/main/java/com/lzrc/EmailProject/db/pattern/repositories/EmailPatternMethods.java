package com.lzrc.EmailProject.db.pattern.repositories;

import com.lzrc.EmailProject.db.embeddables.EmailEmbeddable;

public interface EmailPatternMethods<T> {

	T findIdByEmailEmbeddable(EmailEmbeddable emailEmbeddable);
	
}

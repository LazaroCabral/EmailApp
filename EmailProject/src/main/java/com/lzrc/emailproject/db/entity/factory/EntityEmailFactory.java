package com.lzrc.emailproject.db.entity.factory;

import org.springframework.objenesis.instantiator.basic.NewInstanceInstantiator;

import com.lzrc.emailproject.db.AbstractEmail;
import com.lzrc.emailproject.db.AbstractEmailErrors;

public class EntityEmailFactory<T extends AbstractEmail<? extends AbstractEmailErrors<T>>> {

	public T emailFactory(Class superEmailclass) {
		
		NewInstanceInstantiator<T> instantiator= new NewInstanceInstantiator<>(superEmailclass);
		return instantiator.newInstance();
	}
	
}

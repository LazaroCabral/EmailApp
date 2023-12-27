package com.lzrc.EmailProject.db.entity.factory;

import java.lang.reflect.Constructor;

import org.springframework.objenesis.instantiator.basic.NewInstanceInstantiator;

import com.lzrc.EmailProject.db.AbstractEmail;
import com.lzrc.EmailProject.db.AbstractEmailErrors;

public class EntityEmailFactory<T extends AbstractEmail<? extends AbstractEmailErrors<T>>> {

	public T emailFactory(Class superEmailclass) {
		
		NewInstanceInstantiator<T> instantiator= new NewInstanceInstantiator<>(superEmailclass);
		return instantiator.newInstance();
	}
	
}

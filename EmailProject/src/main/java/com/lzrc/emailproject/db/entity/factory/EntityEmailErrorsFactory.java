package com.lzrc.emailproject.db.entity.factory;

import org.springframework.objenesis.instantiator.basic.NewInstanceInstantiator;

import com.lzrc.emailproject.db.AbstractEmail;
import com.lzrc.emailproject.db.AbstractEmailErrors;

public class EntityEmailErrorsFactory<T extends AbstractEmailErrors<? extends AbstractEmail<T>>> {

	public T emailErrorFactory(Class superEmailErrorclass) {
		
//		Constructor<T>[] constructors= superEmailclass.getDeclaredConstructors();
//		Constructor<T> defaultConstructor = null;
//		for (Constructor<T> constructor : constructors) { 
//			if(constructor.getParameters().length==0) {
//				defaultConstructor=constructor;
//				break;
//			}
//		}
		NewInstanceInstantiator<T> instantiator= new NewInstanceInstantiator<>(superEmailErrorclass);
		return instantiator.newInstance();
	}
	
}

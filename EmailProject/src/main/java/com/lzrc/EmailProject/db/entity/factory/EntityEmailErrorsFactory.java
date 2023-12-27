package com.lzrc.EmailProject.db.entity.factory;

import java.lang.reflect.Constructor;

import org.springframework.objenesis.instantiator.basic.NewInstanceInstantiator;

import com.lzrc.EmailProject.db.AbstractEmail;
import com.lzrc.EmailProject.db.AbstractEmailErrors;

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

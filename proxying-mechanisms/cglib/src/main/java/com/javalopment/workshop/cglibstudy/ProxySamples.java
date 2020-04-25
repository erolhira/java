package com.javalopment.workshop.cglibstudy;

import java.lang.reflect.Method;

import org.junit.Test;

import net.sf.cglib.beans.BeanGenerator;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.FixedValue;
import net.sf.cglib.proxy.MethodInterceptor;

public class ProxySamples {
	
	/*
	 * The FixedValue is a callback interface that simply returns the value from the proxied method.
	 */
	@Test
	public void caseReturningSameValue() {
		
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(PersonService.class);
		enhancer.setCallback((FixedValue) () -> "Hello Fixed.");
		
		PersonService proxy = (PersonService) enhancer.create();
		String res = proxy.sayHello(null);
		System.out.println(res);
		
		//There are some drawbacks here because we are not able to decide which method a proxy should intercept.
		//ClassCastException
		//Integer lengthOfName = proxy.lengthOfName("this is my name"); 
	}
	
	@Test
	public void caseReturningValueDependingOnMethodSignature() {
		
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(PersonService.class);
		enhancer.setCallback((MethodInterceptor) (obj, method, args, proxy) -> {
			
			System.out.println("method.getClass: " + method.getClass()); //java.lang.reflect.Method
			System.out.println("method.getDeclaringClass: " + method.getDeclaringClass()); //com.jtudy.workshop.cglibstudy.PersonService
			System.out.println("method.getName: " + method.getName()); //sayHello
			System.out.println("method.getReturnType: " + method.getReturnType()); //java.lang.String
			
			if(method.getDeclaringClass() != Object.class && method.getReturnType() == String.class) {
				return "Hello caseReturningValueDependingOnMethodSignature.";
			} else {
				return proxy.invokeSuper(obj, args);
			}
		});
		
		PersonService proxy = (PersonService) enhancer.create();
		
		System.out.println(proxy.sayHello("null"));
		System.out.println("length: " + proxy.lengthOfName("My name"));
	}
	
	@Test
	public void beanCreator() throws Exception {
		
		BeanGenerator beanGenerator = new BeanGenerator();
		beanGenerator.addProperty("name", String.class);
		Object myBean = beanGenerator.create();
		Method setter = myBean.getClass().getMethod("setName", String.class);
		setter.invoke(myBean, "some string value set by a cglib");
		
		Method getter = myBean.getClass().getMethod("getName");
		
		System.out.println(getter.invoke(myBean));
	}
	
	
}

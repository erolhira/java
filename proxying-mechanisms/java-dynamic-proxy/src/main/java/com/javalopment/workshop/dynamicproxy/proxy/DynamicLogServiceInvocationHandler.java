package com.javalopment.workshop.dynamicproxy.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Date;

import com.javalopment.workshop.dynamicproxy.service.ILogService;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
public class DynamicLogServiceInvocationHandler implements InvocationHandler {

	@Getter @Setter @NonNull
	private ILogService logService;
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		
		String text = (String) args[0];
		args[0] = "[" + new Date() + "] " + text;
		
		Object result = method.invoke(logService, args);		
		return result;
	}
	
}

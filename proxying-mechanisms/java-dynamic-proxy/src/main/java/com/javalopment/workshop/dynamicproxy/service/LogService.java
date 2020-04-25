package com.javalopment.workshop.dynamicproxy.service;

public class LogService implements ILogService {

	@Override
	public void log(String text) {
		
		System.out.println(text);
	}
	
}

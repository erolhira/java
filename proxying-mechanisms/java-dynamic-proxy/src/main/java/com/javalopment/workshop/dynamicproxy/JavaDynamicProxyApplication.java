package com.javalopment.workshop.dynamicproxy;

import java.lang.reflect.Proxy;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.javalopment.workshop.dynamicproxy.proxy.DynamicLogServiceInvocationHandler;
import com.javalopment.workshop.dynamicproxy.service.ILogService;
import com.javalopment.workshop.dynamicproxy.service.LogService;

@SpringBootApplication
public class JavaDynamicProxyApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(JavaDynamicProxyApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		ILogService logService = new LogService();
		DynamicLogServiceInvocationHandler handler = new DynamicLogServiceInvocationHandler(logService);
		
		ILogService proxiedLogService = (ILogService) 
				Proxy.newProxyInstance(ILogService.class.getClassLoader(), new Class[] {ILogService.class}, handler);
		
		proxiedLogService.log("Hello Dynamic Proxy");
	}

}

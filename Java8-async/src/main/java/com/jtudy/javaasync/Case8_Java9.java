package com.jtudy.javaasync;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class Case8_Java9 {
	
	/*
	 * java.util.concurrent.TimeoutException is thrown.
	 */
	@Test
	public void testOrTimeOut() {
		
		try {
			longWork().orTimeout(1000, TimeUnit.MILLISECONDS).get();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCompleteOnTimeout() {
		
		try {
			String defaultValue = "55";
			String value = longWork().completeOnTimeout(defaultValue, 1000, TimeUnit.MILLISECONDS).get();
			System.out.println(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * whenComplete could report other exceptional completions that might occur before the timeout occurs.
	 */
	@Test
	public void testWhenComplete() {
		
		try {
			CompletableFuture.supplyAsync(() -> {
				if(true) throw new RuntimeException("exception is thrown..");
				return "1";
			}).orTimeout(1000, TimeUnit.MILLISECONDS)
			.whenComplete((result, error) -> {
				if(error == null) {
					System.out.println("result -> " + result);
				} else {
					System.out.println(error);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public CompletableFuture<String> longWork(){
		CompletableFuture<String> work = CompletableFuture.supplyAsync(() -> {
			sleep(5000);
			return "1";
		});
		return work;
	}
	
	private void sleep(int sleep) {
		try {
			Thread.sleep(sleep);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

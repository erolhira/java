package com.jtudy.javaasync;

import java.util.concurrent.CompletableFuture;

import org.junit.Test;

/*
 * Static methods runAsync and supplyAsync allow us to simply execute some code asynchronously 
 * without any dependency to old Runnable interface.
 * Use supplyAsync if you want to do something with the result of the method, and runAsync if you don't.
 */
public class Case3_SupplyAsync {

	@Test
	public void testSupplyAsync() {
		
		CompletableFuture.supplyAsync(() -> {
			sleep(500);
			System.out.println("supplyAsync called.");
			return null;
		});
		System.out.println("in testSupplyAsync");
		sleep(600);
	}
	
	@Test
	public void testSupplyAsync2() throws Exception {
		
		CompletableFuture<Boolean> future1 = CompletableFuture.supplyAsync(() -> {
			sleep(500);			
			return true;
		});
		
		CompletableFuture<Boolean> future2 = CompletableFuture.supplyAsync(() -> {
			sleep(800);			
			return true;
		});
		
		if(future1.get() && future2.get()) {
			System.out.println("testSupplyAsync2 is called.");
		}
	}

	private void sleep(int sleep) {
		try {
			Thread.sleep(sleep);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

package com.jtudy.javaasync;

import java.util.concurrent.CompletableFuture;

import org.junit.Test;

public class Case4_ThenApply {

	/*
	 * The thenApply accepts a Function instance, uses it to process the result 
	 * and returns a Future that holds a value returned by a function.
	 */
	@Test
	public void thenApply() throws Exception {
		
		String result = 
		CompletableFuture
			.supplyAsync(() -> { 
					sleep(1500); 
					return "supplyAsync";
					})
			.thenApply(s -> { 
				System.out.println("printed in first thenApply since async"); 
				return s + " is followed by thenApply";})
			.thenApply(s -> s + ".")
			.get();
		
		System.out.println(result);
	}
	
	/*
	 * The thenAccept method receives a Consumer and passes it the result of the computation. 
	 * The final future.get() call returns an instance of the Void type.
	 */
	@Test
	public void thenAccept() throws Exception {
				
		CompletableFuture.supplyAsync(() -> "supplyAsync")
			.thenApply(s -> s + " is followed by thenApply")
			.thenAccept(s -> System.out.println(s))
			.get();
	}
	
	/*
	 * The thenRun method's input and output are void. 
	 */
	@Test
	public void thenRun() throws Exception {
				
		CompletableFuture.supplyAsync(() -> "supplyAsync")
			.thenApply(s -> s + " is followed by thenApply")
			.thenRun(() -> System.out.println("computation is done."))
			.get();
	}
	
	private void sleep(int sleep) {
		try {
			Thread.sleep(sleep);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

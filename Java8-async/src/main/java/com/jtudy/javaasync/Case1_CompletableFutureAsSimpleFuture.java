package com.jtudy.javaasync;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Test;

/*
 * CompletableFuture implements both the Future and the CompletionStage interface.
 * CompletionStage is a promise. 
 */
public class Case1_CompletableFutureAsSimpleFuture {

	@Test
	public void testSimpleFuture() throws Exception {
		
		for(int i = 0; i < 3; i++) {
			Future<String> future = asyncCalculation();
			System.out.println(future.get());
		}
	}
	
	public Future<String> asyncCalculation() {
		
		CompletableFuture<String> completableFuture = new CompletableFuture<>();
		
		Executors.newCachedThreadPool().submit(() -> {
			//do job here
			completableFuture.complete("completed thread --> " + Thread.currentThread().getId());
			return null;
		});
		
		return completableFuture;
	}
}

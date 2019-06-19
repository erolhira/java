package com.jtudy.javaasync;

import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Test;

/*
 * You may want to cancel the execution of a Future.
 */
public class Case2_CompletableFutureAsSimpleFutureWithCancellation {

	int count = 0;
	
	@Test
	public void testSimpleFutureWithCancellation() throws Exception {
		
		for(int i = 0; i < 4; i++) {
			count = i;
			Future<String> future = asyncCalculation();
			try {
				System.out.println(future.get());
			} catch (CancellationException e) {
				System.out.println("async job cancelled when count: " + count);
			}
		}
	}
	
	public Future<String> asyncCalculation() {
		
		CompletableFuture<String> completableFuture = new CompletableFuture<>();
		
		Executors.newCachedThreadPool().submit(() -> {
			//do job here
			if(count < 3) {				
				completableFuture.complete("completed thread --> " + Thread.currentThread().getId());
			} else {
				completableFuture.cancel(true); //CancellationException is thrown if the future is canceled.
			}
			return null;
		});
		
		return completableFuture;
	}
}

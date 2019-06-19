package com.jtudy.javaasync;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

/*
 * to execute multiple Futures in parallel and wait for all of them to execute and then process their combined results:
 * CompletableFuture.allOf or join could be used.
 */
public class Case6_MultipleFuturesInParallel {

	@Test
	public void testAllOf() throws InterruptedException, ExecutionException {
		
		CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "Call 1");
		CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "Call 2");
		CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> "Call 3");
		
		CompletableFuture<Void> combined = CompletableFuture.allOf(future1, future2, future3);
		combined.get();
		
		System.out.println(future1.get());
		System.out.println(future2.get());
		System.out.println(future3.get());		
	}
	
	@Test
	public void testJoin() {
		
		CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "Call 1");
		CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "Call 2");
		CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> "Call 3");
		
		String combined = Stream.of(future1, future2, future3)
				.map(CompletableFuture::join)
				.collect(Collectors.joining(" "));
		
		System.out.println(combined);
	}
	
}

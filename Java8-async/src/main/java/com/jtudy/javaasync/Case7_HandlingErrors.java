package com.jtudy.javaasync;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.junit.Test;

public class Case7_HandlingErrors {

	@Test
	public void testException() {
		
		CompletableFuture<String> future = CompletableFuture.supplyAsync(
			() -> {
				throw new RuntimeException("exception in execution..");
			}
		);
		
		try {
			future.get();
		} catch (Exception e) {		
			e.printStackTrace();
		}
	}
	
	@Test
	public void testHandle() {
		
		CompletableFuture<String> future = CompletableFuture.supplyAsync(
			() -> {
				throw new RuntimeException("exception in execution..");
			}
		).handle((s, t) -> s == null ? t.getMessage() : String.valueOf(s));
		
		try {
			String str = future.get();
			System.out.println(str);
		} catch (Exception e) {		
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCompleteWithException() {
		
		CompletableFuture<String> future = new CompletableFuture<>();
		future.completeExceptionally(new RuntimeException("failed.."));
		try {
			future.get();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

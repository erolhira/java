package com.jtudy.javaasync;

import java.util.concurrent.CompletableFuture;

import org.junit.Test;

/*
 * thenApply is used if you have a synchronous mapping function.
 * 
 * thenCompose is used if you have an asynchronous mapping function (i.e. one that returns a CompletableFuture). 
 * It will then return a future with the result directly, rather than a nested future.
 * 
 * 
 * ** thenCompose can be used to chain two Futures sequentially.
 * **If you want to execute two independent Futures and do something with their results, use the thenCombine method.
 */
public class Case5_ThenCombine {

	/*
	 * ** If you want to execute two independent Futures and do something with their results, use the thenCombine method
	 */
	@Test
	public void testThenCombine() {
		
		CompletableFuture.supplyAsync(() -> "Mr. ")
			.thenCombine(
				getUserInfo("Erol")
				.thenCombine(getUserAddress("Istanbul"), (s1, s2) -> s1 + s2), 
				(s1, s2) -> s1 + s2
			)
			.thenAccept(s -> System.out.println(s));
		sleep(3000);
	}
	
	/*
	 * thenCombine executes async function and each call to thenCombine run in asycn order also, but the results are combined in order to call to each other.
	 */
	@Test
	public void testThenCombine2() {
		
		CompletableFuture.supplyAsync(() -> "Mr. ")
			.thenCombine(
				getUserInfo("Erol"), (s1, s2) -> s1 + s2
			)
			.thenCombine(getUserAddress("Istanbul"), (s1, s2) -> s1 + s2)
			.thenAccept(s -> System.out.println(s));
		sleep(3000);
	}
	
	/*
	 * thenCompose executes an async function, but runs after the execution of previous async function ends.
	 * ** thenCompose can be used to chain two Futures sequentially. 
	 */
	@Test
	public void testThenCompose() {
		
		CompletableFuture.supplyAsync(() -> { sleep(2000); System.out.println("in supplyAsync"); return "Mr. "; })
			.thenCompose(s -> getUserInfo(s + "Erol "))
			.thenCompose(s -> getUserAddress(s + "Istanbul"))
			.thenAccept(s -> System.out.println(s));
		
		System.out.println("after call to supplyAsync");
		
		sleep(6000);
	}
	
	/*
	 * asynchrounously calls getUserInfo and getUserAddress.
	 * The thenAcceptBoth method could be used when you want to do something with two Futures' results, but don't need to pass any resulting value down a Future chain.
	 */
	@Test
	public void testThenAcceptBoth() {
		getUserInfo("Erol ")
		.thenAcceptBoth(getUserAddress("Istanbul"),
				(s1, s2) -> System.out.println(s1 + s2));
		sleep(4000);
	}
	
	private CompletableFuture<String> getUserInfo(String name){
		return CompletableFuture.supplyAsync(() -> { 
			sleep(1500); 
			System.out.println("in getUserInfo");
			return "name: " + name; }
		);
	}
	
	private CompletableFuture<String> getUserAddress(String city){
		return CompletableFuture.supplyAsync(() -> { System.out.println("in getUserAddress"); return city;});
	}
	
	private void sleep(int sleep) {
		try {
			Thread.sleep(sleep);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

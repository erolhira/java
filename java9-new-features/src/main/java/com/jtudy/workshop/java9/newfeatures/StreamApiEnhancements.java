package com.jtudy.workshop.java9.newfeatures;

import java.util.stream.Stream;

import org.junit.Test;

public class StreamApiEnhancements {

	/*
	 * Stream.iterate(initial value, next value)
	 */
	@Test
	public void streamIterateJava8() {
		
		Stream.iterate(0, n -> n + 1)
	                .limit(10)
	                .forEach(x -> System.out.println(x));
	}
	
	/*
	 * The version in JDK 8 took one parameter as the seed and created an infinite stream as output. 
	 * JDK 9 adds an overloaded method that takes three parameters, 
	 * which effectively gives you the ability to replicate the standard for loop syntax as a stream. 
	 * For example, Stream.iterate(0, i -> i < 5, i -> i + 1) gives you a stream of integers from 0 to 4. 
	 * 
	 * Stream.iterate(initial value, stopper predicate, next value)
	 */
	@Test
	public void streamIterateJava9() {
		
		Stream.iterate(1, n -> n < 20 , n -> n * 2)
        	.forEach(x -> System.out.println(x));			
	}
	
	@Test
	public void takeWhile() {
		
		Stream.iterate("", s -> s + "t")
				  .takeWhile(s -> s.length() < 10)
				  .reduce((first, second) -> second) //find last
				  .ifPresent(s -> System.out.println(s));
				  ;		
	}
		
	/*
	 * The dropWhile operation will remove elements while the given predicate for an element returns true 
	 * and stops removing on the first predicate's false.
	 */
	@Test
	public void dropWhile() {
		
		System.out.print("when ordered:");
		Stream.of(1,2,3,4,5,6,7,8,9,10)
			.dropWhile(x -> x < 4)
			.forEach(a -> System.out.print(" " + a));
		
		System.out.println();
		System.out.print("when unordered:");
		Stream.of(1,2,4,5,3,7,8,9,10)
			.dropWhile(x -> x < 4)
			.forEach(a -> System.out.print(" " + a));
	}
	
	@Test
	public void extractNullValuesJava8 () {
		
		Stream.of("1", "2", null, "4")
			.forEach(s -> System.out.print(s));
		
		System.out.println("");
		
		Stream.of("1", "2", null, "4")
			.flatMap(s -> s != null ? Stream.of(s) : Stream.empty())
			.forEach(s -> System.out.print(s));
	}
	
	@Test
	public void extractNullValuesJava9 () {
		
		Stream.of("1", "2", null, "4")
			.flatMap(s -> Stream.ofNullable(s))
			.forEach(s -> System.out.print(s));
	}
}

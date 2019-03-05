package com.jtudy.java8_lambdas;

import java.util.stream.IntStream;

import org.junit.Test;

/*
 * Reduction is an operation that takes a sequence of input elements 
 * and combines them into a single summary result by repeated application of a combining operation.
 * 
 * If the combining function is associative, reduction parallelizes cleanly.
 * Associative means the order does not matter. The result is the same irrespective of the order used to combine elements.
 * 
 * Examples of: sum, min, max, average, count
 * .count() is equivalent to .map(e -> 1).sum().
 * 
 * Warning: If you pass a nonassociative function to reduce, you will get the wrong answer. The function must be associative.
 * 
 * - the larger the data set, the more likely parallel processing is going to show an improvement in performance.
 * - A system needs to have a least four cores available to the JVM before you will see any substantial difference in performance.
 * - As a general guideline, a data set should contain more than 10,000 items before showing a difference in performance.
 * - Any operations or complex operations that cause threads to block will have a negative impact on performance.
 */
public class Reduction {

	@Test
	public void testReduction(){
		
		/*
		 * Note that the integer value of 0 is passed into the reduce method. This is called the identity value. 
		 * It represents the starting value for the reduce function and the default return value if there are no members in the reduction.
		 */
		int result = IntStream.rangeClosed(1, 5).parallel().reduce(0, (sum, element) -> sum + element);
		System.out.println("sum of [1, 5]: " + result);
	}
	
	
}

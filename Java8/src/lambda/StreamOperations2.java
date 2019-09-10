package lambda;

import java.util.stream.Stream;

import org.junit.Test;

public class StreamOperations2 {

	/*
	 * Stream.iterate(initial value, next value)
	 */
	@Test
	public void streamIterate() {
		
		Stream.iterate(0, n -> n + 1)
	                .limit(10)
	                .forEach(x -> System.out.println(x));
		
		//Stream of odd numbers only
		Stream.iterate(0, n -> n + 1)
					.filter(x -> x % 2 != 0) //odd
					.limit(10)
					.forEach(x -> System.out.println(x));
		
		//fibonacci
		Stream.iterate(new int[]{0, 1}, n -> new int[]{n[1], n[0] + n[1]})
					.limit(20)
					.map(n -> n[0])
					.forEach(x -> System.out.println(x));
		
		//sum all the fibonacci values
		int sum = Stream.iterate(new int[]{0, 1}, n -> new int[]{n[1], n[0] + n[1]})
	                .limit(10)
	                .map(n -> n[0]) // Stream<Integer>
	                .mapToInt(n -> n)
	                .sum();
        System.out.println("Fibonacci 10 sum : " + sum);
        
	}
}

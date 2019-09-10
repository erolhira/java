package com.jtudy.java8_lambdas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

public class StreamOperations {
	
	private List<Integer> numbers = new ArrayList<>(Arrays.asList(4, 2, 5, 8, 12, 3, 6, 9, 7));
	private List<String> names = new ArrayList<>(Arrays.asList("Zeynep", "Erol", "Hilal"));
	
	/*
	 * Extracting data with map
	 * map(Function<? super T,? extends R> mapper)
	 * primitive versions: mapToInt(), mapToLong(), mapToDouble()
	 */
	@Test
	public void testMapOperation(){
		
		List<Integer> squares = numbers.stream().filter(p -> p > 5).map(i -> i * i).collect(Collectors.toList());
		squares.stream().forEach(t -> System.out.println(t));
	}	
	
	/*
	 * peek(Consumer<? super T> action)
	 * Great for printing intermediate results. Debugging-purposed operation
	 * Caution: With the peek method, you can change element data in the stream. 
	 * Any changes will be made to the underlying collection. 
	 * However, this would not be a best practice as the data would not be accessed in a thread-safe manner. 
	 * Manipulating the data in this way is strongly discouraged.
	 */
	@Test
	public void testPeek(){
		
		names.stream()
			.filter(p -> p.length() > 2)
			.peek(c -> System.out.println("Filtered Value: " + c))
			.map(m -> m.toLowerCase())
			.map(m -> m + ".")			
			.peek(c -> System.out.println("Lower Case Value: " + c))
			.collect(Collectors.toList());		
	}
	
	@Test
	public void testPeek2(){
		
		names.stream()
			.filter(p -> p.length() > 2)
			.peek(c -> System.out.println("Filtered Value 2: " + c))
			.filter(p -> p.length() > 4)
			.peek(c -> System.out.println("Filtered Value 4: " + c))
			.collect(Collectors.toList());		
	}
	
	@Test
	public void testAllMatch(){
		boolean allGreaterThan1 = numbers.stream().allMatch(p -> p > 1);
		System.out.println("allGreatedThan1: " + allGreaterThan1);
		
		boolean allGreaterThan5 = numbers.stream().allMatch(p -> p > 5);
		System.out.println("allGreatedThan5: " + allGreaterThan5);		
	}
		
}

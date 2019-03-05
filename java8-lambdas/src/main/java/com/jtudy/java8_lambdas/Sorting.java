package com.jtudy.java8_lambdas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.junit.Test;

public class Sorting {

	private List<Integer> numbers = new ArrayList<>(Arrays.asList(4, 2, 5, 8, 12, 3, 6, 9, 7));
	private List<String> names = new ArrayList<>(Arrays.asList("Zeynep", "Erol", "Hilal"));
	
	@Test
	public void sort(){
		
		names.stream().sorted().forEach(p -> System.out.println(p));
		
		numbers.stream()
			.sorted((a, b) -> Integer.compare(a, b))
			.sorted(Comparator.reverseOrder())
			.forEach(p -> System.out.println(p));
	}	
}

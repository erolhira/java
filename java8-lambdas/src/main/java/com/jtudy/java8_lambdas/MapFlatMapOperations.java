package com.jtudy.java8_lambdas;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

public class MapFlatMapOperations {

	private List<List<String>> listOfGroups = Arrays.asList(
		Arrays.asList("Galatasaray", "Trabzonspor"), 
		Arrays.asList("Barcelona", "Real Madrid", "Real Sociedad"),
		Arrays.asList("Juventus", "Milan")
	);
	
	@Test
	public void test_map() {

		listOfGroups.stream().findFirst().ifPresent(
				group -> group.stream().map(t -> t + " - " + t.length()).forEach(length -> System.out.println(length)));

		List<List<String>> list = listOfGroups.stream().map(listOfGroupsObject -> listOfGroupsObject)
				.collect(Collectors.toList());
		System.out.println(list);
	}
	
	@Test
	public void test_flatMap() {
		
		listOfGroups.stream().flatMap(group -> group.stream()).forEach(t -> 
			System.out.println(t)
		);
		
		System.out.println("-----------------");
		System.out.println("Teams starting with G:");
		listOfGroups.stream()
				.flatMap(group -> group.stream()
				.filter(t -> t.startsWith("G"))).forEach(t -> 
			System.out.println(t)
		);
	}
}

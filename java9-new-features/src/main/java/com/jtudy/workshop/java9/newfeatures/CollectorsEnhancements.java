package com.jtudy.workshop.java9.newfeatures;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;

import java.util.Arrays;

import org.junit.Test;

/*
 * In Java 9, two new Collectors have been added to the Collectors utility class: 
 * 	1. Collectors.filtering 
 * 	2. Collectors.flatMapping
 */
public class CollectorsEnhancements {

	List<String> names = List.of("erol", "hilal", "zeynep", "ayca");
	
	/*
	 *  With this type of filtering the resulting map would not contain an entry for that names whose lengths less than 5 (that is, no key and no value).
	 *  prints:
	 	5: [hilal]
		6: [zeynep]
	 */
	@Test
	public void filteringNamesByLengthGt4WithJava8Filter() {
		
		Map<Integer, List<String>> map = names.stream()
				.filter(t -> t.length() > 4)
				.collect(groupingBy(t -> t.length()));
		
		map.forEach((k,v) -> System.out.println(k + ": " + v));
	}
	
	/*
	 * 	Java 9 - filtering
	 * 	prints:
	 	4: []
		5: [hilal]
		6: [zeynep]
	 */
	@Test
	public void filteringNamesByLengthGt4WithJava9Filtering() {
		
		Map<Integer, List<String>> map = names.stream()
				.collect(groupingBy(t -> t.length(), filtering(t -> t.length() > 4, toList())));
		map.forEach((k,v) -> System.out.println(k + ": " + v));
	}
	
	/*
	 * collect, groupingBy, averagingLong, summingInt, maxBy, comparingLong, joining
	 */
	@Test
	public void mappingWithJava8() {
		
		Map<Integer, List<String>> defaultGrouping = names.stream().collect(groupingBy(t -> t.length()));
		
		Map<Integer, Set<String>> mappingSet = names.stream().collect(groupingBy(t -> t.length(), toSet()));
		
		mappingSet = names.stream().collect(groupingBy(t -> t.length(), mapping(t -> t, toSet())));
		
		//grouping by multiple fields
		Map<Integer, Map<Integer, Set<String>>> multipleFieldsMap = names.stream().collect(groupingBy(t -> t.length(), groupingBy(t -> t.hashCode(), toSet())));
		
		//average w.r.t lengths
		Map<Integer, Double> averagesOfHashes = names.stream().collect(groupingBy(t -> t.length(), averagingLong(t -> t.hashCode())));
		
		//sum w.r.t lengths
		Map<Integer, Long> sumOfHashes = names.stream().collect(groupingBy(t -> t.length(), summingLong(t -> t.hashCode())));
		
		//max or min hashCode from group
		//maxBy and minBy collectors take into account the possibility that the collection to which it is applied could be empty. 
		//This is why the value type in the map is Optional<BlogPost>.
		Map<Integer, Optional<String>> maxNames = names.stream().collect(groupingBy(t -> t.length(), maxBy(comparingLong(t -> t.hashCode()))));
		
		Map<Integer, String> joinedMap = names.stream().collect(groupingBy(t -> t.length(), mapping(t -> t, joining(", ", "Joins To Lengths[", "]"))));
		joinedMap.forEach((k,v) -> System.out.println(k + ": " + v));		
	}
	
	/*
	 * Both the collectors mapping and flatMapping takes a function and a collector where the elements are collected 
	 * but flatMapping function accepts a Stream of elements which is then accumulated by the collector.
	 * 
	 * Note that the flatMapping collector is related to the flatMap method from the Stream API. 
	 * That method takes a function producing a Stream of zero or more elements for each element in the input Stream. 
	 * The result is then flattened into a single Stream.
	 */
	@Test
	public void flatMappingWithJava9() {
		
		Map<Integer, Set<String>> withMapping 		= names.stream().collect(groupingBy(t -> t.length(), mapping(t -> t, toSet())));
		Map<Integer, Set<String>> withFlatMapping 	= names.stream().collect(groupingBy(t -> t.length(), flatMapping(t -> Stream.of(t), toSet())));
		
		Map<Object, Set<List<Character>>> withMapping2 = names.stream().collect(groupingBy(t -> t.length(), mapping(t -> t.chars().mapToObj(c -> (char) c).collect(toList()), toSet())));
		Map<Object, Set<Character>> withFlatMapping2 = names.stream().collect(groupingBy(t -> t.length(), flatMapping(t -> t.chars().mapToObj(c -> (char) c).collect(toList()).stream(), toSet())));				
	}
	
}

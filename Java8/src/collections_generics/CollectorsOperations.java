package collections_generics;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.Set;
import java.util.stream.Collectors;
import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;

import org.junit.Test;

public class CollectorsOperations {

	@Test
	public void studyCollectors() {
		
		List<String> list = Arrays.asList(new String[]{"1", "2", "3", "4", "4"});
		
		//count
		long count = list.stream().filter(t -> !t.equals("1")).count();
		System.out.println("count: " + count);
		
		//max
		list.stream().filter(t -> !t.equals("1"))
			.max(Comparator.comparing(t -> Integer.parseInt(t)))
			.ifPresent(t -> System.out.println("max: " + t));
		
		//sum
		double sum = list.stream().mapToDouble(t -> Double.parseDouble(t)).sum();
		System.out.println("sum" + list + ": " + sum);
		
		//average
		OptionalDouble d1 = list.stream().mapToDouble(t -> Double.parseDouble(t)).average();
		d1.ifPresent(t -> System.out.println("average" + list + ": " + t));
		
		//sort
		List<String> newlist = list.stream().sorted(Comparator.comparing(t -> Integer.parseInt(t)))
			.collect(Collectors.toList());
		newlist.stream().forEach(t -> System.out.println("newlist: " + t));
		
		//concatenate
		String str = list.stream().map(t -> t).distinct().sorted().collect(Collectors.joining(", "));
		System.out.println("concatanated list: " + str);		
		
		//groupingBy		
		Map<Integer, List<String>> map = list.stream().collect(Collectors.groupingBy(t -> t.hashCode()));
		map.forEach((k,v) -> System.out.println(k + ": " + v));
		
		Map<Integer, Long> summingMap = list.stream().collect(Collectors.groupingBy(t -> t.hashCode(), Collectors.summingLong(t -> t.length())));
		summingMap.forEach((k,v) -> System.out.println(k + ": " + v));
	}
	
	/*
	 * collect, groupingBy, averagingLong, summingInt, maxBy, comparingLong, joining
	 */
	@Test
	public void mappingWithJava8() {
		
		List<String> names = Arrays.asList("erol", "hilal", "zeynep", "ayca");
		
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
}

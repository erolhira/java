package collections_generics;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

import org.junit.Test;

/*
 * The forEach method has been added to all collections.
 * The Collection interface extends the Iterable interface. 
 * The Iterable interface defines the forEach method.
 * Filter method uses Predicate lambdas to select items.
 */
public class CollectionsTest {
	
	public static final Predicate<Person> YOUNG = p -> p.getAge() >= 18 && p.getAge() <= 25;
	public static final Predicate<Person> CHILD = p -> p.getAge() >= 2 && p.getAge() < 18;
	public static final Predicate<Person> BABY = p -> p.getAge() < 2;
	
	@Test
	public void testTreeSet(){
		
		Set<Integer> orderedSet = new TreeSet<>(Arrays.asList(4, 2, 5, 8, 1, 3, 6, 9, 7));
		orderedSet.stream().forEach((t) -> System.out.println(t));		
	}
	
	@Test
	public void testMap(){
		
		Map<String, String> map = new TreeMap<>();
		map.put("1", "One");
		map.put("2", "Two");
		map.put("3", "Three");
		
		map.compute("2", (k, v) -> v == "Two" ? v : null);
		map.compute("3", (k, v) -> null); //if computed value is null then the entry is removed from the map
		map.compute("4", (k, v) -> "Four"); //4 is put into map, because not in the map
		
		map.forEach((k, v) -> System.out.println(k + ": " + v));
	}
	
	@Test
	public void testStack(){
		
		Deque<String> stack = new ArrayDeque<>();
		stack.push("1");
		stack.push("2");
		stack.push("3");
		
		System.out.println("max in the stack: " + 
				Collections.max(stack, (s, t) -> Integer.compare(Integer.valueOf(s), Integer.valueOf(t))));
		System.out.println("min with max method via using different Comparator: " + 
				Collections.max(stack, (s, t) -> Integer.compare(1/Integer.valueOf(s), 1/Integer.valueOf(t))));
		while(!stack.isEmpty()){
			System.out.println(stack.pop());
		}		
	}
	
	@Test
	public void testFiltersWithPredicates(){
		
		List<Person> persons = Person.createShortList();
		
		persons.stream()
			.filter((p) -> p.getAge() > 25 && p.getAge() < 50)
			.forEach((p) -> System.out.println("MAN: " + p));
		
		persons.stream().filter(YOUNG).forEach(p -> System.out.println("YOUNG: " + p));
		persons.stream().filter(CHILD).forEach(p -> System.out.println("CHILD: " + p));
		persons.stream().filter(BABY).forEach(p -> System.out.println("BABY: " + p));
	}
	
	@Test
	public void testMethodReferences(){
		
		List<Person> persons = Person.createShortList();
		
		persons.stream().forEach(p -> p.print());
		persons.stream().forEach(Person::print);				
	}
	
	@Test
	public void testMultipleFilters(){
		
		List<Person> persons = Person.createShortList();
		persons.stream()
			.filter(YOUNG)
			.filter(t -> t.getGivenName().startsWith("J"))			
			.forEach(Person::printSummary);
	}
	
	@Test
	public void testConsumerInterface(){
		
		Consumer<Person> consumer = t -> t.printSummary();
		List<Person> persons = Person.createShortList();
		
		persons.stream().filter(YOUNG).forEach(consumer);		
		consumer.accept(persons.get(0));
	}
	
	@Test
	public void testFunctionInterface(){
		
		Function<Person, String> f = t -> t.getGivenName();
		String name = f.apply(Person.createShortList().get(0));
		System.out.println(name);
	}
	
	@Test
	public void testUnaryOperator(){
		//If you need to pass in something and return the same type, use the UnaryOperator interface.
		UnaryOperator<String> upperCase = t -> t.toUpperCase();
		System.out.println(upperCase.apply("test"));
	}
	
	
	
}

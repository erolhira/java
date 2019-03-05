package com.jtudy.java8_lambdas;

import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

import org.junit.Test;

import com.jtudy.java8_lambdas.testmodel.Person;

/*
 * - consumer is an expression that performs operations on an object passed as argument and has a void return type
 * - Function transforms a T to a R
 * - Supplier provides an instance of a T (such as a factory); it takes no arguments but it returns some value. 
 * - Predicate takes one parameter and returns a boolean
 * - BiPredicate takes two parameters and returns a boolean
 */
public class BuiltinFunctionalInterfaces {

	public static final Predicate<Person> YOUNG = p -> p.getAge() >= 18 && p.getAge() <= 25;
	public static final Predicate<Person> CHILD = p -> p.getAge() >= 2 && p.getAge() < 18;
	public static final Predicate<Person> BABY = p -> p.getAge() < 2;
	
	@Test
	public void testConsumerInterface(){
		
		Consumer<Person> consumer = t -> t.printSummary();
		List<Person> persons = Person.createShortList();
		
		//call consumer.accpet for each of filtered persons
		persons.stream().filter(YOUNG).forEach(consumer);		
		
		//call consumer.accept for the first person
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
	
	@Test
	public void testBiPredicate(){
		boolean equals = equals("erol", "hira", (a, b) -> a.equals(b));
		System.out.println(equals);
	}
	
	public boolean equals(String a, String b, BiPredicate<String, String> equalsPredicate){
		return equalsPredicate.test(a, b);
	}
	
	@Test
	public void supplierTest(){
		display(() -> 4);
	}
	
	public void display(Supplier<Integer> arg){
		System.out.println(arg.get());
	}
	
	@Test
	public void consumerTest(){
		printLog("this log is via Consumer interface", (c) -> System.out.println(c));
	}
	
	@Test
	public void transformerTest(){
		String greeting = transformIntoGreeting("Erol", (r) -> "Hello " + r);
		System.out.println(greeting);
	}		
	
	public String transformIntoGreeting(String name, Function<String, String> transformer){
		return transformer.apply(name);
	}
	
	public void printLog(String log, Consumer<String> logger){
		logger.accept(log);
	}
}

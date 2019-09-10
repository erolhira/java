package com.jtudy.workshop.java9.newfeatures;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.junit.Test;

public class OptionalClassEnhancements {

	//This is Java 8; returns unwrapped value
	@Test
	public void defaultValueWithOrElseGet() {
	    String nullName = null;
	    String name = Optional.ofNullable(nullName).orElseGet(() -> "test");
	    assertEquals("test", name);
	}
	
	//This is Java 9 [or] method; returns Optional in case of null.
	//or(Supplier supplier): This method is useful when you want to ensure that you always have an Optional.
	@Test
	public void defaultValueWithOr() {
	    
		Optional<String> defaultValue = Optional.of("test");	    	
	    Optional<String> name = Optional.ofNullable((String) null).or(() -> defaultValue);
	    assertEquals(defaultValue.get(), name.get());
	}
	
	/*
	 * ifPresentOrElse(Consumer action, Runnable emptyAction):
	 * Similar to ifPresent, but if there is no value, it executes the emptyAction.
	 */
	@Test
	public void ifPresentOrElse() {
		
		// given
		Optional<String> value = Optional.of("properValue");
		AtomicInteger successCounter = new AtomicInteger(0);
		AtomicInteger onEmptyOptionalCounter = new AtomicInteger(0);

		// when
		value.ifPresentOrElse(
					v -> successCounter.incrementAndGet(), 		//ifPresent 
					onEmptyOptionalCounter::incrementAndGet); 	//orElse

		//Note, that the callback passed as the second argument was not executed.
		//In the case of an empty Optional, the second callback gets executed.
		
		// then
		System.out.println("successCounter: " + successCounter.get());
		System.out.println("onEmptyOptionalCounter: " + onEmptyOptionalCounter.get());
	}
	
	/*
	 * Java 9 allows us to treat the Optional instance as a Stream.
	 * stream(): Returns a stream of zero or one elements, depending on whether there is a value.
	 */
	@Test
	public void optionalAsStream() {
	    
		// given
	    Optional<String> value = Optional.of("a");
	 
	    // when
	    List<String> collect = value.stream().map(String::toUpperCase).collect(Collectors.toList());	
	}	
	
}

package lambda;

import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import org.junit.Test;

/*
 * - consumer is an expression that performs operations on an object passed as argument and has a void return type
 * - Function transforms a T to a R
 * - Supplier provides an instance of a T (such as a factory); it takes no arguments but it returns some value. 
 * - Predicate takes one parameter and returns a boolean
 * - BiPredicate takes two parameters and returns a boolean
 */
public class BuiltinFunctionalInterfaces {

	@Test
	public void testBiPredicate(){
		boolean equals = equals("erol", "hira", (a, b) -> a.equals(b));
		System.out.println(equals);
	}
	
	@Test
	public void supplierTest(){
		display(() -> 4);
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
	
	public void display(Supplier<Integer> arg){
		System.out.println(arg.get());
	}
	
	public String transformIntoGreeting(String name, Function<String, String> transformer){
		return transformer.apply(name);
	}
	
	public void printLog(String log, Consumer<String> logger){
		logger.accept(log);
	}
	
	public boolean equals(String a, String b, BiPredicate<String, String> equalsPredicate){
		return equalsPredicate.test(a, b);
	}
}

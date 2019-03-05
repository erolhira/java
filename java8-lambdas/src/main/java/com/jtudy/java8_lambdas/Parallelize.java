package com.jtudy.java8_lambdas;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import com.jtudy.java8_lambdas.testmodel.Salary;

/*
 * Your data should be immutable or read-only when used with stream pipelines. 
 * No changes to state should take place during a pipeline.
 */
public class Parallelize {

	@Test
	public void testMapToDouble() {

		List<Salary> salaries = Arrays.asList(new Salary(12.2D, 2), new Salary(20.0D, 4), new Salary(30.5D, 2));

		Double sum = salaries.stream().filter(p -> p.getSalary() > 0).mapToDouble(p -> p.getSalary()).sum();
		System.out.println("sum of salaries: " + sum);
	}
	
	@Test
	public void testMapToDoubleParallel(){
		List<Salary> salaries = Arrays.asList(new Salary(12.2D, 2), new Salary(20.0D, 4), new Salary(30.5D, 2));

		Double sum = salaries.parallelStream()
						.filter(p -> p.getSalary() > 0)
						.mapToDouble(p -> p.getSalary())
						.sum();
		System.out.println("sum of salaries: " + sum);
	}
	
	/*
	 * Both the sequential and parallel methods may be called in a pipeline. 
	 * Whichever method is called last, will be applied to the stream.
	 */
	@Test
	public void testMapToDoubleParallel2(){
		List<Salary> salaries = Arrays.asList(new Salary(12.2D, 2), new Salary(20.0D, 4), new Salary(30.5D, 2));

		Double sum = salaries.stream()
						.filter(p -> p.getSalary() > 0)
						.mapToDouble(p -> p.getSalary())
						.parallel()
						.sum();
		System.out.println("sum of salaries: " + sum);
	}
	
	/*
	 * If you want to save the results after a pipeline completes, use the collect method and Collectors class as shown in the example. 
	 * This method parallelizes well and treats the data in a stateless way.
	 */
	@Test
	public void parallelFilter(){
		
		List<Salary> salaries = Arrays.asList(new Salary(12.2D, 2), new Salary(20.0D, 4), new Salary(30.5D, 2));
		salaries = salaries.parallelStream()
						.filter(p -> p.getSalary() > 0)
						.collect(Collectors.toList());
	}
}

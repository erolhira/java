package com.jtudy.workshop.java9.newfeatures;

public interface InterfaceChanges {
	
	/*
	 * In Java SE 7 or earlier versions;
	 * 1. Constant variables
	 * 2. Abstract methods
	 */
	
	static final String TEST_CONSTANT = "TEST CONSTANT";
	Integer sum(Integer a, Integer b);
	abstract Integer multiply(Integer a, Integer b);
	
	/*
	 * With Java 8;
	 * 1. Constant variables
	 * 2. Abstract methods
	 * 3. Default methods
	 * 4. Static methods
	 */
	
	default Integer divide(Integer a, Integer b) {
		return a / b;
	}
	
	static Integer increment(Integer a) {
		return a + 1;
	}
	
	/*
	 * With Java 9;
	 * Constant variables
	 * Abstract methods
	 * Default methods
	 * Static methods
	 * Private methods
	 * Private Static methods
	 */
	
	private void log(String log) {
		System.out.println(log);
	}
	
	private static void debug(String log) {
		System.out.println(log);
	}
}

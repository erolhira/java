package com.jtudy.java8_lambdas.custominterface;

@FunctionalInterface
public interface StringAnalyzer {

	public boolean operate(String source, String target);
}

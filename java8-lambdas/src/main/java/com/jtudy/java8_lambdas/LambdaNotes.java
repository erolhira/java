package com.jtudy.java8_lambdas;

/*
 * JSR335: Lambda Expressions
 * Only the following parts of Lambda expressions are on the OCA exam:
 * - Functional Interfaces
 * - Lambda Expressions
 * 
 * The general concept of closures, including lambda expressions, 
 * is the ability to work with a function that can be stored as a variable (for example, a first-class method).
 * 
 * A language that considers procedures to be "first-class" 
 * allows functions to be passed around just like any other value.
 * 
 * Lambda expressions must have a functional interface, also called a single abstract method (SAM).
 * 
 * FIs provide target types for lambda expressions as well as method references. 
 * 
 * FIs must also have exactly one abstract method. 
 * Because the name of the method is known, the method name is excluded from the actual lambda expression.
 * 
 * An FI may have one or more default methods and one or more static methods. 
 * Default methods allow for the addition of new code to the interface, ensuring backward compatibility. 
 * This ensures that legacy code that implements the interface will not break 
 * if the new default methods are not used. 
 * (Note that default and static methods in the context of FIs are not on the exam.)
 * 
 * Instances of functional interfaces can be created with 
 * method references, constructor references, and lambda expressions. 
 * 
 * Lambda expressions allow for the creation and use of single method classes.
 * 
 * Lambda expressions can appear in any of these contexts: 
 * assignments, array initializers, cast expressions, constructor arguments, 
 * lambda bodies, method arguments, return statements, ternary expressions, and variable declarations.
 * 
 * The body of lambda expressions that return a value are considered to be value-compatible, 
 * and those that do not are considered to be void-compatible.
 */
public class LambdaNotes {
	
	
}

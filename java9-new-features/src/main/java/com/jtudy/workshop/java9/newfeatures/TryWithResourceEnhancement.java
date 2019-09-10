package com.jtudy.workshop.java9.newfeatures;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import org.junit.Test;

public class TryWithResourceEnhancement {

	@Test
	public void tryWithResourceJava7() {
		
		StringWriter sw = new StringWriter();		
		try(PrintWriter pw = new PrintWriter(sw)) {
			pw.println("test");
			System.out.print(sw.toString());
		} finally {
			try {
				sw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/*
	 * Effectively final variables can be used in try-with-resources without being reassigned. 
	 * Currently, each resource that is used must be assigned to a new variable, 
	 * even if that resource has already been defined in the method. 
	 * With this change, it is now possible to use an existing variable 
	 * (as long as it is effectively final) without reassigning it.
	 */
	@Test
	public void tryWithResourceJava9() throws Exception {
		
		StringWriter sw = new StringWriter();		
		try(sw; PrintWriter pw = new PrintWriter(sw)) {
			pw.println("test");
			System.out.print(sw.toString());
		} 
	}
}

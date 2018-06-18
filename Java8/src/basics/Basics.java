package basics;

import org.junit.Test;

public class Basics {

	public static final Integer TYPE_2 = 2;
	public static final Integer TYPE_127 = 127;
	public static final Integer TYPE_128 = 128;
	
	@Test
	public void testIntegerEquality(){
		
		Integer two = 2;				
		System.out.println(two == TYPE_2);
		
		Integer a = 127;
		System.out.println(a == TYPE_127);
		System.out.println(127 == TYPE_127);
		
		//**
		a = 128;
		System.out.println(a == TYPE_128);
		System.out.println(128 == TYPE_128);
	}
}

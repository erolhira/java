package enums;

import org.junit.Test;

public class EnumTest {
	
	@Test
	public void testSimpleEnum(){
		System.out.println("State is " + SimpleState.ON);
	}
	
	@Test
	public void testComplexEnum(){
		System.out.println(PowerState.ON.getDescription());
	}	
}

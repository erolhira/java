package collections_generics;

import org.junit.Test;

public class GenericTest {
	
	@Test
	public void testGenerics(){
		
		CacheAny<String> str = new CacheAny<>();
		str.setAny("cached str");
		System.out.println(str.getAny());
	}
}

package basics;

import org.junit.Test;

public class SystemBasics {
	
	@Test
	public void basics(){
		
		long freeMemoryInJvm = Runtime.getRuntime().freeMemory();
		System.out.println("freeMemoryInJvm: " + (freeMemoryInJvm/(1024*1024)) + " MB");
		
		long totalMemoryInJvm = Runtime.getRuntime().totalMemory();
		System.out.println("totalMemoryInJvm: " + (totalMemoryInJvm/(1024*1024)) + " MB");
		
		long maxMemoryForJvm = Runtime.getRuntime().maxMemory(); //-Xmx
		System.out.println("maxMemoryForJvm: " + (maxMemoryForJvm/(1024*1024)) + " MB"); 
		
		int availableProcessors = Runtime.getRuntime().availableProcessors();
		System.out.println("availableProcessors: " + availableProcessors);
				
	}
}

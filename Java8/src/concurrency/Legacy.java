package concurrency;

import org.junit.Test;

public class Legacy {
	
	@Test
	public void testByExtendingThread(){
		new ExampleThread().start();
	}
	
	@Test
	public void testByImplementingRunnable(){
		new Thread(new ExampleRunnable("ExampleRunnable")).start();
	}
}

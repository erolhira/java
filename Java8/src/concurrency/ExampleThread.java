package concurrency;

public class ExampleThread extends Thread {
	
	@Override
	public void run() {
		/*
		 * The code to be executed in a new thread of execution should be placed in a run method. 
		 * You should avoid calling the run method directly. 
		 * Calling the run method does not start a new thread 
		 * and the effect would be no different than calling any other method.
		 */
		System.out.println("i am inside ExampleThread.run");
	}
}

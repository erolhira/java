package concurrency;

/*
 * A daemon thread is a thread that does not prevent the JVM from exiting 
 * when the program finishes but the thread is still running. 
 * 
 * An example for a daemon thread is the garbage collection.
 * 
 * You can use the setDaemon(boolean) method to change the Thread daemon properties 
 * before the thread starts.
 */
public class Daemon {
	
	public static void main(String[] args){
		
		Thread thread = new Thread(){
			//by default this is not daemon thread
			@Override
			public void run() {
				int i = 0;
				while(true){
					System.out.println("i: " + i++);
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		
		thread.setDaemon(true);
		thread.start();
		
		System.out.println("done.");
	}
}

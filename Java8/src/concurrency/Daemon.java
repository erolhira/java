package concurrency;

public class Daemon {
	
	public static void main(String[] args){
		
		Thread thread = new Thread(){
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

package concurrency;

public class ExampleRunnable implements Runnable {
	
	private String name;

	public ExampleRunnable(String name) {
		this.name = name;
	}
	
	@Override
	public void run() {
		for(int i = 0; i < 7; i++){
			System.out.println("i am inside " + name + ".run -- i: " + i);
		}
	}
	
}
package concurrency;

public class SynchronizedCounter {

	private static int counter = 0;

	public synchronized void increment() {
		counter++;
	}

	public synchronized void decrement() {
		counter--;
	}

	public synchronized int getValue() {
		return counter;
	}
}

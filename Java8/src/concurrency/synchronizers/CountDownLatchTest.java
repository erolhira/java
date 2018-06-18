package concurrency.synchronizers;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * CountDownLatch is a synchronization aid that allows one or more threads to wait 
 * until a set of operations being performed in other threads completes.
 * 
 * In countDownLatch, there is one or more threads, that are waiting for a set of other threads to complete. 
 * In this situation, there are two types of threads, one type is waiting, another type is doing something, 
 * after finishes their tasks, they could be waiting or just terminated.
 */
public class CountDownLatchTest {

	public static void main(String args[]) {
		
		CountDownLatch latch = new CountDownLatch(3);
		
		Waiter waiter = new Waiter(latch);
		Decrementer d1 = new Decrementer(latch);
		Decrementer d2 = new Decrementer(latch);
		Decrementer d3 = new Decrementer(latch);
		
		ExecutorService es = Executors.newCachedThreadPool();
		es.execute(waiter);
		es.execute(d1);
		es.execute(d2);
		es.execute(d3);
		es.shutdown();
	}
	
	static class Waiter implements Runnable {
		
		CountDownLatch latch;

		public Waiter(CountDownLatch latch) { 
			this.latch = latch;
		}

		@Override
		public void run() {
			try {
				latch.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			System.out.println("Waiter Released");
		}		
	}
	
	static class Decrementer implements Runnable {

		CountDownLatch latch = null;

		public Decrementer(CountDownLatch latch) {
			this.latch = latch;
		}

		@Override
		public void run() {

			try {
				Thread.sleep(1000);
				System.out.println("decrementing latch..");
				latch.countDown();				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

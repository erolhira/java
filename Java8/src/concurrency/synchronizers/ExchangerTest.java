package concurrency.synchronizers;

import java.util.concurrent.Exchanger;

/*
 * The java.util.concurrent.Exchanger class represents a kind of rendezvous point 
 * where two threads can exchange objects.
 * 
 * Waits for another thread to arrive at this exchange point 
 * (unless the current thread is interrupted), 
 * and then transfers the given object to it, receiving its object in return.
 */
public class ExchangerTest {

	public static void main(String[] args){
		
		Exchanger<String> exchanger = new Exchanger<>();
		ExchangerRunnable exchangerRunnable1 = new ExchangerRunnable(exchanger, "A");
		ExchangerRunnable exchangerRunnable2 = new ExchangerRunnable(exchanger, "B");

		new Thread(exchangerRunnable1).start();
		new Thread(exchangerRunnable2).start();
	}
	
	static class ExchangerRunnable implements Runnable {

		Exchanger<String> exchanger = null;
		String object = null;

		public ExchangerRunnable(Exchanger<String> exchanger, String object) {
			this.exchanger = exchanger;
			this.object = object;
		}

		@Override
		public void run() {
			try {
				Object previous = this.object;
				this.object = this.exchanger.exchange(this.object);
				System.out.println(Thread.currentThread().getName() + " exchanged " + previous + " for " + this.object);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

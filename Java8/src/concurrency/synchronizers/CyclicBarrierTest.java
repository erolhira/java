package concurrency.synchronizers;

import java.util.concurrent.Callable;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

/*
 * CyclicBarrier is a synchronization aid that allows a set of threads to all wait for each other to reach a common barrier point.
 * CyclicBarrier takes an (optional) Runnable task which is run once the common barrier condition is met.
 * 
 * In CyclicBarrier, there are only one type of threads, they are waiting for each other.
 */
public class CyclicBarrierTest {

	private static AtomicInteger counter = new AtomicInteger();
	final CyclicBarrier barrier = new CyclicBarrier(6, new BarrierEndAction());

	@Test
	public void testAtomicVariables() throws InterruptedException, ExecutionException {
		 
		
		ExecutorService es = Executors.newCachedThreadPool();
		Future<Integer> f1 = es.submit(new CounterCallable());
		Future<Integer> f2 = es.submit(new CounterCallable());
		Future<Integer> f3 = es.submit(new CounterCallable());
		Future<Integer> f4 = es.submit(new CounterCallable());
		Future<Integer> f5 = es.submit(new CounterCallable());
		Future<Integer> f6 = es.submit(new CounterCallable());						
	}
	
	class CounterCallable implements Callable<Integer> {

		@Override
		public Integer call() throws Exception {
			int res = counter.incrementAndGet();
			System.out.println("before await counter: " + res);
			if(false) barrier.reset(); //barrier could be reset in some condition
			barrier.await();
			System.out.println("after all threads have done increment, counter is : " + counter.get());
			return res;
		}		
	}
	
	class BarrierEndAction implements Runnable {

		@Override
		public void run() {
			System.out.println("Barrier ends..");
		}
		
	}

}

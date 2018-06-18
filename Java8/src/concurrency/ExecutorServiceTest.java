package concurrency;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class ExecutorServiceTest {
	
	@Test
	public void executorServiceWithRunnable(){
		
		ExecutorService executorService = Executors.newCachedThreadPool();
		executorService.execute(new ExampleRunnable("Runnable_1"));
		executorService.execute(new ExampleRunnable("Runnable_2"));
		executorService.execute(new ExampleRunnable("Runnable_3"));
		executorService.execute(new ExampleRunnable("Runnable_4"));
		
		//Stop accepting new Callables.
		executorService.shutdown();
		
		try {
			//If you want to wait for the Callables to finish
			executorService.awaitTermination(5, TimeUnit.SECONDS);
		} catch (InterruptedException ex) {
			System.out.println("Stopped waiting early");
		}
	}
	
	@Test
	public void executorServiceWithCallable(){
		
		ExecutorService executorService = Executors.newCachedThreadPool();
		Future<String> f1 = executorService.submit(new ExampleCallable("f1", 100));
		Future<String> f2 = executorService.submit(new ExampleCallable("f2", 202));
		Future<String> f3 = executorService.submit(new ExampleCallable("f3", 300));
		Future<String> f4 = executorService.submit(new ExampleCallable("f4", 400));
		Future<String> f5 = executorService.submit(new ExampleCallable("f5", 500));
		List<Future<String>> futures = new ArrayList<>(Arrays.asList(f1, f2, f3, f4, f5));
		
		int i = 0;
		final int lastStep = futures.size();
		
		while(i != lastStep){
			ListIterator<Future<String>> iter = futures.listIterator();
			while(iter.hasNext()){
				Future<String> future = iter.next();
				if(future.isDone()){ //isDone() is non-blocking.
					i++;
					try {
						System.out.println("future finished: " + future.get()); //future.get() is blocking.
					} catch (InterruptedException | ExecutionException e) {
						e.printStackTrace();
					}
					iter.remove();
				}
			}
		}
		
		System.out.println("done.");
	}
}

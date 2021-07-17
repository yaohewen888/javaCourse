package javacourse.homework.week4;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class GetThreadReturnValue2 {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService executorService = Executors.newFixedThreadPool(8);

		Callable<Integer> callable = new Callable<Integer>() {
			public Integer call() throws Exception {
				int r = new Random().nextInt(10);
				r *= 1000;
				Thread.sleep(r);
				System.out.println("future1 random=" + r);
				return r;
			}
		};
		
		Runnable runnable = new Runnable() {
			
			public void run() {
				int r = new Random().nextInt(10);
				r *= 1000;
				try {
					Thread.sleep(r);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("future2 random=" + r);
			}
		};
        Future<Integer> future1 = executorService.submit(callable);
        System.out.println("future1 return=" + future1.get());
        Future<Integer> future2 = executorService.submit(runnable, 99);
        System.out.println("future2 return=" + future2.get());
        System.out.println("Main Thread Exit");
	}
}

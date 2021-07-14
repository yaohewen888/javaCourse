package homework.week4;

public class GetThreadReturnValue {
	
	public static class TestThread extends Thread {
		
		private int result = 0;
		
		public int getResult() {
			return result;
		}
		
		private static int sum() {
	        return fibo(36);
	    }
	    
	    private static int fibo(int a) {
	        if ( a < 2) 
	            return 1;
	        return fibo(a-1) + fibo(a-2);
	    }
		
		@Override
		public void run() {
			result = sum();
			System.out.println("thread exit");
			return;
		}
	}
	
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		TestThread tt = new TestThread();
		tt.start();
		try {
			tt.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("main exit, and test thread result=" + tt.getResult());
		System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
	}
}

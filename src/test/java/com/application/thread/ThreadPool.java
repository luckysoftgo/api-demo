package com.application.thread;

import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadPool {

	public static void main(String[] args) {
		// ExecutorService service = Executors.newCachedThreadPool(); //随时可以扩容的线程池使用.
		ExecutorService service = Executors.newFixedThreadPool(5); // 随时可以扩容的线程池使用.

		MyTask task = new MyTask();
		for (int i = 0; i < 100; i++) {
			//service.submit(task);
		}
		service.shutdown();
		System.err.println(Runtime.getRuntime().freeMemory()/(1024*1024));
		System.err.println(Runtime.getRuntime().totalMemory()/(1024*1024));
		System.err.println(Runtime.getRuntime().maxMemory()/(1024*1024));
		System.out.println(Runtime.getRuntime().availableProcessors());
		
	}

	public static class MyTask implements Runnable {
		public void run() {
			System.err.println(System.currentTimeMillis() + ":Thread ID :" + Thread.currentThread().getId());
			try {
				Thread.sleep(100);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}

package com.application.thread;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo {

	private static Lock lock = new ReentrantLock();
	private static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	private static Lock readLock = readWriteLock.readLock();
	private static Lock writeLock = readWriteLock.writeLock();
	private int value;

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		final ReadWriteLockDemo demo = new ReadWriteLockDemo();
		Runnable readRunnable = new Runnable() {
			public void run() {
				try {
					//demo.handlerRead(readLock);
					demo.handlerRead(lock);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		};

		Runnable writeRunnable = new Runnable() {
			public void run() {
				try {
					//demo.handlerWrite(writeLock, new Random().nextInt(100));
					demo.handlerWrite(lock, new Random().nextInt(100));
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		};

		for (int i = 0; i < 50; i++) {
			new Thread(writeRunnable).start();
		}
		for (int i = 50; i < 60; i++) {
			new Thread(readRunnable).start();
		}
		long end = System.currentTimeMillis();
		
		System.out.println("耗费的时间是:"+(end-start)/1000);
		
	}

	public Object handlerRead(Lock lock) throws InterruptedException {
		try {
			lock.lock();
			Thread.sleep(10);
			System.out.println(Thread.currentThread().getName()+"读取到的值是:"+value);
			return value;
		}
		finally {
			lock.unlock();
		}
	}

	public void handlerWrite(Lock lock, int index) throws InterruptedException {
		try {
			lock.lock();
			Thread.sleep(10);
			value = index;
			System.out.println(Thread.currentThread().getName()+"写入的值是:"+value);
		}
		finally {
			lock.unlock();
		}
	}

}

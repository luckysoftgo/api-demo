package com.application.thread;

import java.util.Arrays;
import java.util.function.IntConsumer;

public class ThreadDemo {

	public static void main(String[] args) {
		System.out.println("startting ");
		Thread t1 = new Thread(new Demo1("t1"));
		t1.start();
		Thread t2 = new Thread(new Demo2("t2"));
		t2.start();
		Thread t3 = new Thread(new Demo1("t3"));
		t3.start();
		Thread t4 = new Thread(new Demo1("t4"));
		t4.start();
		System.out.println("ending... ");
	}

	public static void arraySort(int[] array) {
		for (int i = 0; i < array.length - 1; i++) {
			for (int j = 0; j < array.length - 1 - i; j++) {
				if (array[j] > array[j + 1]) {
					int temp = array[j];
					array[j] = array[j + 1];
					array[j + 1] = temp;
				}
			}
		}
	}

}

class Demo1 implements Runnable {
	String tag;

	public Demo1(String tag) {
		this.tag = tag;
	}

	public void run() {
		Thread.yield();
		System.out.println("Demo1:" + tag + "," + Thread.currentThread().getName());
	}
}

class Demo2 implements Runnable {
	String tag;

	public Demo2(String tag) {
		this.tag = tag;
	}

	public void run() {
		System.out.println("Demo2:" + tag + "," + Thread.currentThread().getName());
	}
}
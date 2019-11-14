package com.javabase.disruptor.demo1;

/**
 * @author : 孤狼
 * @NAME: LongEvent
 * @DESC: 生产者要生产的【event对象】，主动传递给disruptor中的RingBuffer
 * @DATE: 2019年9月3日
 **/
public class LongEvent {
	private long value;
	
	public long getValue() {
		return value;
	}
	
	public void setValue(long value) {
		this.value = value;
	}
}

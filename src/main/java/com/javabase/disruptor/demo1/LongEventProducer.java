package com.javabase.disruptor.demo1;

import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;
/**
 * @author : 孤狼
 * @NAME: LongEventProducer
 * @DESC: 生产者定义方式一
 * @DATE: 2019年9月3日
 **/
public class LongEventProducer {
	
	private final RingBuffer<LongEvent> ringBuffer;
	
	public LongEventProducer(RingBuffer<LongEvent> ringBuffer){
		this.ringBuffer = ringBuffer;
	}
	
	/**
	 * onData用来发布事件，每调用一次就发布一次事件， 它的参数bb会通过事件传递给消费者
	 * 将bb存入ringBuffer环形事件容器
	 * 生产数据需要遵循的四个步骤
	 */
	public void onData(ByteBuffer bb){
		//1.可以把ringBuffer看做一个环形事件队列，那么next就是得到下面一个空的事件槽索引
		long sequence = ringBuffer.next();
		try {
			//2.用上面的索引取出一个空的事件用于填充（获取该序号对应的事件对象）
			LongEvent event = ringBuffer.get(sequence);
			//3.获取要通过事件传递的业务数据
			event.setValue(bb.getLong(0));
		} finally {
			//4.发布事件
			/**
			 * 注意，最后的 ringBuffer.publish 方法必须包含在 finally 中以确保必须得到调用；
			 * 如果某个请求的 sequence 未被提交，将会堵塞后续的发布操作或者其它的 producer。
			 * 只有发布后才能被监听的消费者消费掉
			 */
			ringBuffer.publish(sequence);
		}
	}
}

package com.javabase.disruptor.demo1;

import com.lmax.disruptor.EventHandler;

/**
 * @author : 孤狼
 * @NAME: LongEventHandler
 * @DESC: 定义disruptor的【消费者】
 *  我们还需要一个事件消费者实现disruptor的EventHandler，也就是一个事件处理器。
 *  这个事件处理器简单地把事件中存储的数据打印到终端：
 * @DATE: 2019年9月3日
 **/
public class LongEventHandler implements EventHandler<LongEvent> {
	
	@Override
	public void onEvent(LongEvent longEvent, long l, boolean b) throws Exception {
		System.out.println("Disruptor的 EventHandler 事件处理器: " + longEvent.getValue());
	}
}

package com.javabase.disruptor.demo1;

import com.lmax.disruptor.EventFactory;

/**
 * @author : 孤狼
 * @NAME: LongEventFactory
 * @DESC: 实现disruptor的【事件工厂】EventFactory，让disruptor批量产生longEvent
 * @DATE: 2019年9月3日
 **/
public class LongEventFactory implements EventFactory {
	
	@Override
	public Object newInstance() {
		return new LongEvent();
	}
}
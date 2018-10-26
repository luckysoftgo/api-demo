package com.application.redis.mq;

import redis.clients.jedis.Jedis;

/**
 * @desc 订阅者( 先启动, 在到 Publisher 中)
 * @author 孤狼
 */
public class Subscriber {
	
	public static void main(String[] args) {
		RedisMQListener listener1 = new RedisMQListener();
		Jedis jedis1 = TestUtils.getJedis();
		jedis1.psubscribe(listener1, new String[] { "chan*" });
		
		RedisMQListener listener2 = new RedisMQListener();
		Jedis jedis2 = TestUtils.getJedis();
		jedis2.psubscribe(listener2, new String[] { "testchannel" });
		
	}
}

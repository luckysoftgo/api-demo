package com.application.redis.mq;

import redis.clients.jedis.Jedis;

/**
 * @desc 发布者信息.
 * @author 孤狼.
 */
public class Publisher {
	
	public static void main(String[] args) {
		Jedis jedis = TestUtils.getJedis();
		jedis.publish("channel1", "message from channel1: hello world!");
		jedis.publish("channel2", "message from channel2: we are the best!");
		
		jedis.publish("testchannel","this is subscribe test mq message.");
	}
}

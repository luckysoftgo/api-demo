package com.application.redis.mq;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 *@des 测试
 *@author 孤狼
 */
public class TestUtils {
	
	public static JedisPoolConfig getJedisPoolConfig() {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxIdle(0);
		config.setMaxWaitMillis(1000);
		return config;
	}
	
	public static JedisPool getJedisPool() {
		JedisPool pool = new JedisPool(getJedisPoolConfig(), "101.201.177.32", 16339, 60000, "02681330-d47f-4e34-a0fc-4a2f8931c523");
		return pool;
	}
	
	public static Jedis getJedis() {
		Jedis jedis = getJedisPool().getResource();
		return jedis;
	}
}

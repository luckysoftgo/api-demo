package com.application.redis;

import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * @NAME: ConnectTest
 * @DESC:
 * @USER: admin
 * @DATE: 2019年5月14日
 **/
public class ConnectTest {
	
	public static void main(String[] args)  {
		Jedis jedis=new Jedis("118.24.157.96",26339);
		Set<String> keys =jedis.keys("*");
		System.out.println(keys);
	}
	
}

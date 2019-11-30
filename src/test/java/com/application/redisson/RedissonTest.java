package com.application.redisson;

import com.application.base.cache.redisson.redisson.factory.RedissonInstanceSessionFactory;
import com.application.base.cache.redisson.redisson.lock.RedissonDelegateDistributedLock;
import com.application.base.core.BaseJunit4Test;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * @desc redisson 测试.
 * @author 孤狼.
 */
public class RedissonTest extends BaseJunit4Test {
	
	@Autowired
	private RedissonInstanceSessionFactory redissonFactory;
	
	@Autowired
	private RedissonDelegateDistributedLock redissonDistLock;
	
	@Test
	public void distLock() {
		String key = "testLock";
		for (int i = 0; i <500 ; i++) {
			redissonFactory.getRedissonSession().setRString(key+i,"redisson"+i,100);
			System.out.println("index="+i);
		}
	}
	
	@Test
	public void testLock() {
		String key = "testLock";
		String mapKey = "testMap";
		
		boolean flag = redissonDistLock.loopLock(key);
		if (flag) {
			Map<String,Object> params = new HashMap<>(12);
			params.put("a",1);
			params.put("b",2d);
			params.put("c","ccc");
			params.put("d",4f);
			redissonFactory.getRedissonSession().setRMap(mapKey,params);
		}
		Map<Object, Object> map = redissonFactory.getRedissonSession().getRMap(mapKey);
		if (map!=null || map.size() > 0){
			for (Map.Entry<Object, Object> entry : map.entrySet()) {
				System.out.println("得到的結果 key 是：" + entry.getKey()+",value 是："+entry.getValue());
			}
		}
		//解锁操作.
		redissonDistLock.unLock(key);
	}
	
	@Test
	public void testMap() {
		String key = "testMap";
		Map<String,Object> params = new HashMap<>(12);
		params.put("a",1);
		params.put("b",2d);
		params.put("c","ccc");
		params.put("d",4f);
		boolean flag = redissonFactory.getRedissonSession().setRMap(key,params);
		if (flag){
			Map<Object, Object> map = redissonFactory.getRedissonSession().getRMap(key);
			if (map!=null || map.size() > 0){
				for (Map.Entry<Object, Object> entry : map.entrySet()) {
					System.out.println("得到的結果 key 是：" + entry.getKey()+",value 是："+entry.getValue());
				}
			}
		}
		
		redissonFactory.getRedissonSession().setRString(key,"test123",1000);
		System.out.println("得到的结果是:"+redissonFactory.getRedissonSession().getRString(key));
		
	}
	
	
}
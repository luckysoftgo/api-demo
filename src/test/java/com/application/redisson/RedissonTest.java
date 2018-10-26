package com.application.redisson;

import com.application.base.cache.redisson.redisson.factory.RedissonSimpleSessionFactory;
import com.application.base.cache.redisson.redisson.lock.JDelegateDistributedLock;
import com.application.base.core.BaseJunit4Test;
import org.junit.Test;
import org.redisson.RedissonRedLock;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * @desc redisson 测试.
 * @author 孤狼.
 */
public class RedissonTest extends BaseJunit4Test {
	
	@Autowired
	private RedissonClient standalone ;
	
	@Autowired
	private RedissonSimpleSessionFactory redissonFactory;
	
	@Autowired
	private JDelegateDistributedLock redissonDistLock;
	
	@Test
	public void distLock() {
		String key = "testLock";
	}
	
	@Test
	public void testLock() {
		String key = "testLock";
		String mapKey = "testMap";
		
		boolean flag = redissonDistLock.lock(key);
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
	public void test() {
		String key  = "test";
		RBucket bucket = standalone.getBucket(key);
		bucket.set("123456789");
		System.out.println("得到的結果是："+bucket.get());
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
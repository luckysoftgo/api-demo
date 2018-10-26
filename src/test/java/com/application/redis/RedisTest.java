package com.application.redis;

import com.application.base.cache.redis.jedis.lock.DelegateDistributedLock;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.application.base.core.BaseJunit4Test;
import com.application.base.core.datasource.impl.cache.MutilDefaultCacheReadAndWriteDataSessionFactory;
import com.application.base.cache.redis.api.RedisSession;

public class RedisTest extends BaseJunit4Test {

	@Autowired
	private MutilDefaultCacheReadAndWriteDataSessionFactory redisFactory;
	
	@Autowired
	private DelegateDistributedLock distributedLock;
	
	@Test
	public void distLock() {
		String key = "test123";
		boolean distLock = distributedLock.getDistLock(key,key,60000);
		if (distLock){
			System.out.println("被锁住了.");
		}
		boolean isLock = distributedLock.isLock(key);
		if (isLock){
			System.out.println("1..还是锁住的.");
		}
		boolean releaseLock = distributedLock.releaseDistLock(key,key);
		if (releaseLock){
			System.out.println("解锁完成.");
		}else{
			System.out.println("解锁失败.");
		}
		isLock = distributedLock.isLock(key);
		if (isLock){
			System.out.println("2..还是锁住的.");
		}
		
		//得到结果.
		String value = distributedLock.getFactory().getRedisSession().getData(key);
		System.out.println("value:"+value);
	}
	
	@Test
	public void redis() {
		RedisSession redisSession = redisFactory.getCacheReadDataSession().getRedisSession();
		for (int i = 0; i < 2; i++) {
			System.out.println("放入信息完成！！！");
			redisSession.publish("AAA", "往里放信息"+i);
		}
		System.err.println("完成操作!!!");
	}
	
	@Test
	public void tryLock() {
		String key = "redisLock";
		boolean flag = distributedLock.tryLock(key);
		if (flag){
			RedisSession redisSession = redisFactory.getCacheReadDataSession().getRedisSession();
			for (int i = 0; i < 10; i++) {
				redisSession.publish("AAA", "往里放信息"+i);
			}
			System.err.println("完成操作!!!");
		}
		distributedLock.unLock(key);
	}
	
}

package com.application.redis.mq;

import com.application.base.core.BaseJunit4Test;
import com.application.base.core.datasource.impl.cache.MutilDefaultCacheReadAndWriteDataSessionFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @desc MQ 的设置方式.
 * @author 孤狼.
 */
public class MqRedisTest extends BaseJunit4Test {
	
	@Autowired
	private MutilDefaultCacheReadAndWriteDataSessionFactory redisFactory;
	
	/**
	 * 这个在 Test 中表现的不是很好,最好是放在 application 应用中去才最好
	 */
	@Test
	public void testMQ(){
	
		//先监听
		testSub();
		
		System.out.println("监听开始....");
		try{
			Thread.sleep(5000);
		}catch(Exception e){
		}
		System.out.println("监听开始....停止 5 秒结束.");
		
		//再发布信息
		testPub();
	}
	
	private void testSub(){
		RedisMQListener listener = new RedisMQListener();
		redisFactory.getCacheReadDataSession().getRedisSession().psubscribe(listener,new String[]{"hello_*"});
	}
	
	private void testPub(){
		redisFactory.getCacheReadDataSession().getRedisSession().publish("hello_foo", "bar123");
		redisFactory.getCacheReadDataSession().getRedisSession().publish("hello_test", "hello watson");
	}
	
}

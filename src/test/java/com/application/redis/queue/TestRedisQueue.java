package com.application.redis.queue;

import com.application.base.core.BaseJunit4Test;
import com.application.base.core.datasource.impl.cache.MutilDefaultCacheReadAndWriteDataSessionFactory;
import com.application.base.utils.json.JsonConvertUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @desc 测试队列
 * @author 孤狼
 */
public class TestRedisQueue extends BaseJunit4Test {
	
	@Autowired
	private MutilDefaultCacheReadAndWriteDataSessionFactory redisFactory;
	
	String key = "queuetest";
	
	@Test
	public void testQueue() throws Exception {
		// push
		push();
		
		//
		pull();
	}
	
	private void push() throws Exception {
		for (int i = 0; i < 10; i++) {
			Message message = new Message(i, "测试","这是第" + i + "个内容");
			redisFactory.getCacheReadDataSession().getRedisSession().lpush(key+"-"+i, JsonConvertUtils.toJson(message));
		}
	}
	
	private void pull() throws Exception {
		for (int i = 0; i < 10; i++) {
			String message = redisFactory.getCacheReadDataSession().getRedisSession().lpop(key+"-"+i);
			Message msg  = JsonConvertUtils.fromJson(message,Message.class);
			if (msg != null) {
				System.out.println(msg.getMsgId() + "<---->" + msg.getMsgcontent());
			}
		}
	}
}
